package com.archives.archives.service;

import com.archives.archives.entity.Folio;
import com.archives.archives.repository.FolioRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FolioService {
    private final FolioRepository repository;

    public FolioService(FolioRepository repository) {
        this.repository = repository;
    }

    public List<Folio> getAll() {
        return repository.findAll();
    }

    public Folio getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Folio not found with id: " + id));
    }

    public Folio create(Folio folio) {
        return repository.save(folio);
    }

    public Folio update(Long id, Folio folio) {
        Folio existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Folio not found with id: " + id));
        existing.setPage(folio.getPage());
        existing.setFolio(folio.getFolio());
        existing.setSectionName(folio.getSectionName());
        existing.setIlluminationPosition(folio.getIlluminationPosition());
        existing.setTranscription(folio.getTranscription());
        existing.setZoom(folio.getZoom());
        existing.setIlluminationType(folio.getIlluminationType());
        existing.setDescription(folio.getDescription());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
