package com.app.quora.repository;

import com.app.quora.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question,String> {

    Flux<Question> findByCreatedAtGreaterThanOrderByCreatedAtAsc(Instant cursor, Pageable pageable);
    Flux<Question> findAllByOrderByCreatedAtAsc(Pageable pageable);

    Flux<Question> findByUserId(String userId);
}
