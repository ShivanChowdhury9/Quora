package com.app.quora.mapper;

import com.app.quora.dto.TagRequestDTO;
import com.app.quora.dto.TagResponseDTO;
import com.app.quora.model.Tag;

import java.time.Instant;

public class TagMapper {

    public static Tag tagRequestDTOToTag (TagRequestDTO tag) {
        return Tag.builder()
                .name(tag.getName())
                .userId(tag.getUserId())
                .createdAt(Instant.now())
                .build();
    }

    public static TagResponseDTO tagToTagResponseDTO (Tag tag) {
        return TagResponseDTO.builder()
                .name(tag.getName())
                .id(tag.getId())
                .build();
    }
}
