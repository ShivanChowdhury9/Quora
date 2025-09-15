package com.app.quora.dto;


import com.app.quora.enums.ParentType;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedRedisObjectDTO {
    private String userId;
    private String id;
    private ParentType type;
    private String content;     // question title or answer text -> This can be anything
}
