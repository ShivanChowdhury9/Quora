package com.app.quora.service;

import com.app.quora.dto.QuestionRequestDTO;
import com.app.quora.dto.QuestionResponseDTO;
import com.app.quora.mapper.FeedMapper;
import com.app.quora.mapper.QuestionMapper;
import com.app.quora.model.Question;
import com.app.quora.model.elasticSearch.QuestionElasticDocument;
import com.app.quora.producer.FeedEventProducer;
import com.app.quora.repository.QuestionElasticRepository;
import com.app.quora.repository.QuestionRepository;
import com.app.quora.repository.TagRepository;
import com.app.quora.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;
    private final IQuestionIndexService questionIndexService;
    private final FeedEventProducer questionProducer;

    private final QuestionElasticRepository questionDocumentRepository;

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String questionId) {
        return questionRepository.findById(questionId).map(QuestionMapper::questionToResponseDTO);
    }

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO question) {

        List<String> tagNames = question.getTags();
        Question questionToBeSaved = QuestionMapper.questionRequestDTOToQuestion(question);

        return Flux.fromIterable(tagNames).flatMap(tagRepository::findByName)
                .map( tag -> {
                    questionToBeSaved.getTagIds().add(tag.getId());
                    return tag;
                })
                .then(Mono.fromCallable(() -> questionIndexService.createQuestionIndex(questionToBeSaved)))
                .subscribeOn(Schedulers.boundedElastic())
                .then(questionRepository.save(questionToBeSaved))
                .map(savedQuestion -> {
                    questionProducer.publishFeedEvent(FeedMapper.createFeedObjectFromQuestion(savedQuestion));
                    return QuestionMapper.questionToResponseDTO(savedQuestion);
                });

    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size) {
        Pageable pageable = PageRequest.of(0,size);

        if(CursorUtils.isValidCursor(cursor)) {

            Instant timeStamp=CursorUtils.parseCursor(cursor);
            return questionRepository
                    .findByCreatedAtGreaterThanOrderByCreatedAtAsc(timeStamp,pageable)
                    .map(QuestionMapper::questionToResponseDTO);

        }
        else{

            return questionRepository.findAllByOrderByCreatedAtAsc(pageable)
                    .map(QuestionMapper::questionToResponseDTO);


        }
    }

    @Override
    public Flux<QuestionElasticDocument> searchQuestionsByElasticsearch(String query) {
        return Flux.fromIterable(questionDocumentRepository.findByTitleContainingOrContentContaining(query, query));
    }
}
