package com.app.quora.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "AnswerDocument")
public class Answer {

    @Id
    private String id;

    private String userId;

    private String content;

    private String questionId;

    @CreatedDate
    private Instant createdAt;
}
