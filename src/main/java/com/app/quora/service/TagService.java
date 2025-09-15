package com.app.quora.service;

import com.app.quora.dto.TagRequestDTO;
import com.app.quora.dto.TagResponseDTO;
import com.app.quora.mapper.TagMapper;
import com.app.quora.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService{

    private final TagRepository tagRepository;

    @Override
    public Mono<TagResponseDTO> createHashTag(TagRequestDTO tag) {
        return tagRepository.save(TagMapper.tagRequestDTOToTag(tag)).map(TagMapper::tagToTagResponseDTO);
    }

    @Override
    public Mono<TagResponseDTO> getHashTag(String id) {
        return tagRepository.findById(id).map(TagMapper::tagToTagResponseDTO);
    }
}
