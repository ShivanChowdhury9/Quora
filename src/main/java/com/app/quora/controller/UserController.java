package com.app.quora.controller;


import com.app.quora.dto.UserRequestDTO;
import com.app.quora.dto.UserResponseDTO;
import com.app.quora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

      private final UserService userService;

      @PostMapping(path="/create")
      public Mono<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
          return userService.createUser(user);
      }

      @GetMapping(path="/{ID}")
      public Mono<UserResponseDTO> getUser(@PathVariable(name="ID") String userId) {
          return userService.getUserById(userId);
      }

      @PostMapping(path="/{ID}/follow/{ID2}")
      public Mono<UserResponseDTO> follow(@PathVariable(name="ID") String userId1, @PathVariable(name="ID2") String userId2) {
          return userService.follow(userId1,userId2);
      }

}
