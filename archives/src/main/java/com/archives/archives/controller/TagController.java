package com.archives.archives.controller;

import com.archives.archives.entity.Tag;
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
    public List<Tag> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Tag getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return service.create(tag);
    }

    @PutMapping("/{id}")
    public Tag update(@PathVariable Long id, @RequestBody Tag tag) {
        return service.update(id, tag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
