package com.app.quora.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagRequestDTO {

    private String name;
    private String userId;
}
