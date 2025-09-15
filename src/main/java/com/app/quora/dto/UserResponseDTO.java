package com.app.quora.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String bio;
    private String email;
    private String username;
    private Long followers;
    private Long following;
}
