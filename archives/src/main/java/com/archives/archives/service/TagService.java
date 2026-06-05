package com.archives.archives.service;

import com.archives.archives.dto.TagDTO;
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

    // GET ALL
    public List<TagDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // GET BY ID
    public TagDTO getById(Long id) {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found with id: " + id));
        return toDTO(tag);
    }

    // CREATE
    public TagDTO create(TagDTO dto) {
        Tag entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }


    // UPDATE
    public TagDTO update(Long id, TagDTO dto) {
        Tag existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found with id: " + id));

        existing.setName(dto.getName());

        return toDTO(repository.save(existing));
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // =================================================
    // MAPPING ENTITY ↔ DTO
    // Prépare les données pour l'affichage (DTO)
    // ou pour l'enregistrement en base de données (Entity)
    // =================================================
    public TagDTO toDTO(Tag t) {
        TagDTO dto = new TagDTO();

        dto.setId(t.getId());
        dto.setName(t.getName());

        return dto;
    }

    // Convertit un DTO en Entity pour l'enregistrement 
    // en base de données
    public Tag toEntity(TagDTO dto) {
        Tag t = new Tag();

        t.setId(dto.getId());
        t.setName(dto.getName());

        return t;
    }

}