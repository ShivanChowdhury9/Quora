package com.app.quora.repository;

import com.app.quora.enums.UserRole;
import com.app.quora.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User,String> {

    Mono<User> findByRole(UserRole user);
}
