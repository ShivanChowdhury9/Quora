package com.app.quora.model;

import com.app.quora.enums.ParentType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "UpvoteDocument")
public class Upvote {

    @Id
    private String id;

    private Boolean like;

    private String userId;

    private ParentType type;

    private String parentTypeId;

    @CreatedDate
    private String createdAt;

}
