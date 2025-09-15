package com.app.quora.service;

import com.app.quora.dto.AnswerRequestDTO;
import com.app.quora.dto.AnswerResponseDTO;
import reactor.core.publisher.Mono;

public interface IAnswerService {
    Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answer);

    Mono<AnswerResponseDTO> getAnswerById(String answerId);
}
