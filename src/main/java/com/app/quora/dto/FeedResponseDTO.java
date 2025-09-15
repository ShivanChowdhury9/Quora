package com.app.quora.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedResponseDTO {
    private String userId;
    private String question;
    private String answer;
}
