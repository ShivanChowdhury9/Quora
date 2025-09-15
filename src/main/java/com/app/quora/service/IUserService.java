package com.app.quora.service;

import com.app.quora.dto.UserRequestDTO;
import com.app.quora.dto.UserResponseDTO;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<UserResponseDTO> createUser(UserRequestDTO user);

    Mono<UserResponseDTO> getUserById(String userId);

    Mono<UserResponseDTO> follow(String userId1, String userId2);
}
