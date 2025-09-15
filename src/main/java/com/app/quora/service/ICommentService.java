package com.app.quora.service;

import com.app.quora.dto.CommentRequestDTO;
import com.app.quora.dto.CommentResponseDTO;
import reactor.core.publisher.Mono;

public interface ICommentService {
    Mono<CommentResponseDTO> getComment(String id);

    Mono<CommentResponseDTO> addComment(CommentRequestDTO comment);
}
