package com.app.quora.mapper;

import com.app.quora.dto.CommentRequestDTO;
import com.app.quora.dto.CommentResponseDTO;
import com.app.quora.model.Comment;

import java.time.Instant;

public class CommentMapper {

    public static Comment commentRequestDTOToComment(CommentRequestDTO comment) {
        return Comment.builder()
                .type(comment.getType())
                .content(comment.getContent())
                .parentTypeId(comment.getParentId())
                .userId(comment.getUserId())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static CommentResponseDTO commentToCommentResponseDTO(Comment comment) {
        return CommentResponseDTO.builder()
                .type(comment.getType())
                .parentId(comment.getParentTypeId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .build();
    }
}
