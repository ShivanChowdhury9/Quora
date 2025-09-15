package com.app.quora.mapper;


import com.app.quora.dto.UserRequestDTO;
import com.app.quora.dto.UserResponseDTO;
import com.app.quora.enums.UserRole;
import com.app.quora.model.User;

import java.time.Instant;

public class UserMapper {

    public static UserResponseDTO userToUserResponseDTO(User user) {
        return UserResponseDTO.builder()
                .bio(user.getBio())
                .email(user.getEmail())
                .id(user.getId())
                .username(user.getUsername())
                .followers(user.getFollowersIds() == null ? 0: (long)user.getFollowersIds().size())
                .following(user.getFollowingIds() == null ? 0: (long) user.getFollowingIds().size())
                .build();
    }

    public static User userRequestDTOToUser(UserRequestDTO user) {
        return User.builder()
                .bio(user.getBio())
                .email(user.getEmail())
                .username(user.getUsername())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .role(user.getRole() == null ? UserRole.USER:user.getRole())
                .build();
    }
}
