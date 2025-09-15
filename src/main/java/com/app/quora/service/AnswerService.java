package com.app.quora.service;

import com.app.quora.dto.AnswerRequestDTO;
import com.app.quora.dto.AnswerResponseDTO;
import com.app.quora.mapper.AnswerMapper;
import com.app.quora.mapper.FeedMapper;
import com.app.quora.producer.FeedEventProducer;
import com.app.quora.repository.AnswerRepository;
import com.app.quora.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final FeedEventProducer answerProducer;

    @Override
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answer) {

        return answerRepository.save(AnswerMapper.answerRequestDTOToAnswer(answer))
                .flatMap(savedAnswer ->
                        questionRepository.findById(answer.getQuestionId())
                                .flatMap(question -> {
                                    question.getAnswerIds().add(savedAnswer.getId());
                                    answerProducer.publishFeedEvent(FeedMapper.createFeedObjectFromAnswer(savedAnswer));
                                    return questionRepository.save(question);
                                })
                                .thenReturn(AnswerMapper.answerToAnswerResponse(savedAnswer))
                );

    }

    @Override
    public Mono<AnswerResponseDTO> getAnswerById(String answerId) {
        return answerRepository.findById(answerId).map(AnswerMapper::answerToAnswerResponse);
    }
}
