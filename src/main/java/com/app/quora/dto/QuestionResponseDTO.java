package com.app.quora.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {
    private String id;
    private String userId;
    private String title;
    private String description;
    private Instant createdAt;
    private Integer views;

}
