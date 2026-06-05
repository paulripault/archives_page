package com.archives.archives.service;

import com.archives.archives.entity.Tag;
import com.archives.archives.repository.TagRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public List<Tag> getAll() {
        return repository.findAll();
    }

    public Tag getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found with id: " + id));
    }

    public Tag create(Tag tag) {
        return repository.save(tag);
    }

    public Tag update(Long id, Tag tag) {
        Tag existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found with id: " + id));
        existing.setName(tag.getName());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}