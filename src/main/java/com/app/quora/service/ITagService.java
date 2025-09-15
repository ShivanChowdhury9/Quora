package com.app.quora.service;

import com.app.quora.dto.TagRequestDTO;
import com.app.quora.dto.TagResponseDTO;
import reactor.core.publisher.Mono;

public interface ITagService {
    
    Mono<TagResponseDTO> createHashTag(TagRequestDTO tag);

    Mono<TagResponseDTO> getHashTag(String id);
}
