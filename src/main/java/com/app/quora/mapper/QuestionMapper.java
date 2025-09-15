package com.app.quora.mapper;


import com.app.quora.dto.QuestionRequestDTO;
import com.app.quora.dto.QuestionResponseDTO;
import com.app.quora.model.Question;

import java.time.Instant;
import java.util.ArrayList;

public class QuestionMapper {

    public static QuestionResponseDTO questionToResponseDTO(Question question) {
        return QuestionResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .description(question.getDescription())
                .createdAt(question.getCreatedAt())
                .userId(question.getUserId())
                .views(question.getViews())
                .build();
    }

    public static Question questionRequestDTOToQuestion(QuestionRequestDTO question) {
        return Question.builder()
                .title(question.getTitle())
                .description(question.getDescription())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .userId(question.getUserId())
                .tagIds(new ArrayList<>())
                .build();
    }

}
