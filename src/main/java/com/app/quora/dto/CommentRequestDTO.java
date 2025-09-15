package com.app.quora.dto;


import com.app.quora.enums.ParentType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {
    private String parentId;
    private ParentType type;
    private String content;
    private String userId;
}
