package com.archives.archives.controller;

import com.archives.archives.dto.TagDTO;
import com.archives.archives.service.TagService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @GetMapping
    public List<TagDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TagDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public TagDTO create(@RequestBody TagDTO tagDTO) {
        return service.create(tagDTO);
    }

    @PutMapping("/{id}")
    public TagDTO update(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        return service.update(id, tagDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
