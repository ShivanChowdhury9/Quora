package com.app.quora.dto;

import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDTO {

    private String id;

    private String userId;

    private String content;

    private String questionId;

    private Instant createdAt;
}
