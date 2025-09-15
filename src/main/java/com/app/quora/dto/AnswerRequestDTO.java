package com.app.quora.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDTO {

    private String userId;
    private String questionId;
    private String content;
}
