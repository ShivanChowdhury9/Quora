package com.app.quora.controller;


import com.app.quora.dto.CommentRequestDTO;
import com.app.quora.dto.CommentResponseDTO;
import com.app.quora.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(path="/{ID}")
    public Mono<CommentResponseDTO> getComment(@PathVariable(name="ID") String id) {
        return commentService.getComment(id);
    }

    @PostMapping(path="/create")
    public Mono<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO comment){
        return commentService.addComment(comment);
    }
}
