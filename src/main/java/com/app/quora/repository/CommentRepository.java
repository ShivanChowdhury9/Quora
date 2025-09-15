package com.app.quora.repository;

import com.app.quora.model.Comment;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment,String> {

}
