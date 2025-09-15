package com.app.quora.mapper;

import com.app.quora.dto.AnswerRequestDTO;
import com.app.quora.dto.AnswerResponseDTO;
import com.app.quora.model.Answer;

import java.time.Instant;

public class AnswerMapper {

    public static Answer answerRequestDTOToAnswer(AnswerRequestDTO answer) {
        return Answer.builder()
                .questionId(answer.getQuestionId())
                .userId(answer.getUserId())
                .content(answer.getContent())
                .createdAt(Instant.now())
                .build();
    }

    public static AnswerResponseDTO answerToAnswerResponse(Answer answer){
        return AnswerResponseDTO.builder()
                .id(answer.getId())
                .userId(answer.getUserId())
                .content(answer.getContent())
                .questionId(answer.getQuestionId())
                .createdAt(answer.getCreatedAt())
                .build();
    }
}
