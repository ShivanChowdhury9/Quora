package com.app.quora.service;

import com.app.quora.model.Question;
import com.app.quora.model.elasticSearch.QuestionElasticDocument;
import com.app.quora.repository.QuestionElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements  IQuestionIndexService{

    private final QuestionElasticRepository elasticRepository;

    @Override
    public QuestionElasticDocument createQuestionIndex(Question question) {
        QuestionElasticDocument document = QuestionElasticDocument.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getDescription())
                .userId(question.getUserId())
                .build();

        return elasticRepository.save(document);
    }
}
