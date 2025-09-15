package com.app.quora.controller;


import com.app.quora.dto.AnswerRequestDTO;
import com.app.quora.dto.AnswerResponseDTO;
import com.app.quora.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping(path="/question")
    public Mono<AnswerResponseDTO> createAnswer(@RequestBody AnswerRequestDTO answer){
        return answerService.createAnswer(answer);
    }

    @GetMapping(path="/{ID}")
    public Mono<AnswerResponseDTO> getAnswerById(@PathVariable(name="ID") String answerId){
        return answerService.getAnswerById(answerId);
    }

}
