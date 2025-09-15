package com.app.quora.dto;

import com.app.quora.enums.UserRole;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String bio;
    private String email;
    private String username;
    private UserRole role;

}
