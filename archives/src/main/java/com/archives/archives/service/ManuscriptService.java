package com.archives.archives.service;

import com.archives.archives.dto.ManuscriptDTO;
import com.archives.archives.entity.Manuscript;
import com.archives.archives.entity.Tag;
import com.archives.archives.repository.ManuscriptRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManuscriptService {

    private final ManuscriptRepository repository;

    public ManuscriptService(ManuscriptRepository repository) {
        this.repository = repository;
    }

    // GET ALL
    public List<ManuscriptDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // GET BY ID
    public ManuscriptDTO getById(Long id) {
        Manuscript manuscript = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manuscript not found with id: " + id));
        return toDTO(manuscript);
    }

    // CREATE
    public ManuscriptDTO create(ManuscriptDTO dto) {
        Manuscript entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    // UPDATE
    public ManuscriptDTO update(Long id, ManuscriptDTO dto) {
        Manuscript existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Manuscript not found with id: " + id));

        existing.setTitle(dto.getTitle());
        existing.setCote(dto.getCote());
        existing.setTheme(dto.getTheme());
        existing.setDate(dto.getDate());
        existing.setSupport(dto.getSupport());
        existing.setDimension(dto.getDimension());
        existing.setManuscriptName(dto.getManuscriptName());
        existing.setManufacturingPlace(dto.getManufacturingPlace());
        existing.setConservationPlace(dto.getConservationPlace());
        existing.setLink(dto.getLink());

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
    public ManuscriptDTO toDTO(Manuscript m) {
        ManuscriptDTO dto = new ManuscriptDTO();

        dto.id = m.getId();
        dto.title = m.getTitle();
        dto.cote = m.getCote();
        dto.theme = m.getTheme();
        dto.manuscriptName = m.getManuscriptName();
        dto.date = m.getDate();
        dto.manufacturingPlace = m.getManufacturingPlace();
        dto.conservationPlace = m.getConservationPlace();
        dto.link = m.getLink();
        dto.support = m.getSupport();
        dto.dimension = m.getDimension();

        if (m.getTags() != null) {
            dto.tags = m.getTags()
                    .stream()
                    .map(Tag::getName)
                    .toList();
        }

        return dto;
    }

    // Convertit un DTO en Entity pour l'enregistrement en base de données
    public Manuscript toEntity(ManuscriptDTO dto) {
        Manuscript m = new Manuscript();

        m.setTitle(dto.title);
        m.setCote(dto.cote);
        m.setTheme(dto.theme);
        m.setManuscriptName(dto.manuscriptName);
        m.setDate(dto.date);
        m.setManufacturingPlace(dto.manufacturingPlace);
        m.setConservationPlace(dto.conservationPlace);
        m.setLink(dto.link);
        m.setSupport(dto.support);
        m.setDimension(dto.dimension);

        return m;
    }

}
