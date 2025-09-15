package com.app.quora.repository;

import com.app.quora.model.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AnswerRepository extends ReactiveMongoRepository<Answer,String> {

    Flux<Answer> findByUserId(String userId);
}
