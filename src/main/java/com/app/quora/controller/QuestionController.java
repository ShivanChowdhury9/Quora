package com.app.quora.controller;

import com.app.quora.dto.QuestionRequestDTO;
import com.app.quora.dto.QuestionResponseDTO;
import com.app.quora.model.elasticSearch.QuestionElasticDocument;
import com.app.quora.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

        private final QuestionService questionService;

        @GetMapping(path="/{ID}")
        public Mono<QuestionResponseDTO> getQuestionById(@PathVariable(name="ID") String questionId) {
            return questionService.getQuestionById(questionId);
        }

        @PostMapping(path="/new")
        public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO question) {
            return questionService.createQuestion(question);
        }

        @GetMapping(path="/all")
        public Flux<QuestionResponseDTO> getAllQuestions(@RequestParam(required = false) String cursor,
                                                         @RequestParam(required = false,defaultValue = "2") int size) {
            return questionService.getAllQuestions(cursor,size);
        }

        @GetMapping("/elasticsearch")
        public Flux<QuestionElasticDocument> searchQuestionsByElasticsearch(@RequestParam String query) {
            return questionService.searchQuestionsByElasticsearch(query);
        }

}
