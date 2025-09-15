package com.app.quora.service;


import com.app.quora.dto.QuestionRequestDTO;
import com.app.quora.dto.QuestionResponseDTO;
import com.app.quora.model.elasticSearch.QuestionElasticDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IQuestionService {

    Mono<QuestionResponseDTO> getQuestionById(String questionId);

    Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO question);

    Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size);

    Flux<QuestionElasticDocument> searchQuestionsByElasticsearch(String query);
}
