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
@Document(collection = "TagDocument")
public class Tag {

    @Id
    private String id;

    private String name;

    private String userId;

    @CreatedDate
    private Instant createdAt;

    //not creating bidirectional mapping with Questions
}
