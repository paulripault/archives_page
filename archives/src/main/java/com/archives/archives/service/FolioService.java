package com.archives.archives.service;

import com.archives.archives.dto.FolioDTO;
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

    // GET ALL
    public List<FolioDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // GET BY ID
    public FolioDTO getById(Long id) {
        Folio folio = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Folio not found with id: " + id));
        return toDTO(folio);
    }

    // CREATE
    public FolioDTO create(FolioDTO dto) {
        Folio entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    // UPDATE
    public FolioDTO update(Long id, FolioDTO dto) {
        Folio existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Folio not found with id: " + id));

        existing.setPage(dto.getPage());
        existing.setFolio(dto.getFolio());
        existing.setSectionName(dto.getSectionName());
        existing.setIlluminationPosition(dto.getIlluminationPosition());
        existing.setTranscription(dto.getTranscription());
        existing.setZoom(dto.getZoom());
        existing.setIlluminationType(dto.getIlluminationType());
        existing.setDescription(dto.getDescription());

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
    public FolioDTO toDTO(Folio f) {
        FolioDTO dto = new FolioDTO();

        dto.id = f.getId();
        dto.page = f.getPage();
        dto.folio = f.getFolio();
        dto.sectionName = f.getSectionName();
        dto.illuminationPosition = f.getIlluminationPosition();
        dto.transcription = f.getTranscription();
        dto.zoom = f.getZoom();
        dto.illuminationType = f.getIlluminationType();
        dto.description = f.getDescription();

        return dto;
    }

    // Convertit un DTO en Entity pour l'enregistrement 
    // en base de données
    public Folio toEntity(FolioDTO dto) {
        Folio f = new Folio();

        f.setId(dto.id);
        f.setPage(dto.page);
        f.setFolio(dto.folio);
        f.setSectionName(dto.sectionName);
        f.setIlluminationPosition(dto.illuminationPosition);
        f.setTranscription(dto.transcription);
        f.setZoom(dto.zoom);
        f.setIlluminationType(dto.illuminationType);
        f.setDescription(dto.description);

        return f;
    }
}