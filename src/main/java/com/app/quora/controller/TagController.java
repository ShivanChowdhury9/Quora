package com.app.quora.controller;


import com.app.quora.dto.TagRequestDTO;
import com.app.quora.dto.TagResponseDTO;
import com.app.quora.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/create")
    public Mono<TagResponseDTO> createNewHashTag(@RequestBody TagRequestDTO tag) {
        return tagService.createHashTag(tag);
    }

    @GetMapping("/{ID}")
    public Mono<TagResponseDTO> getHashTag(@PathVariable(name="ID") String id) {
        return tagService.getHashTag(id);
    }
}
