package com.app.quora.service;

import com.app.quora.dto.CommentRequestDTO;
import com.app.quora.dto.CommentResponseDTO;
import com.app.quora.mapper.CommentMapper;
import com.app.quora.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{

    private final CommentRepository commentRepository;


    @Override
    public Mono<CommentResponseDTO> getComment(String id) {
        return commentRepository.findById(id).map(CommentMapper::commentToCommentResponseDTO);
    }

    @Override
    public Mono<CommentResponseDTO> addComment(CommentRequestDTO comment) {
        return commentRepository.save(CommentMapper.commentRequestDTOToComment(comment)).map(CommentMapper::commentToCommentResponseDTO);
    }


}
