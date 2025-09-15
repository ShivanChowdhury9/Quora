package com.app.quora.dto;


import com.app.quora.enums.ParentType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private String userId;
    private String content;
    private String parentId;
    private ParentType type;
}


