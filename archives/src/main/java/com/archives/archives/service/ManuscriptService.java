package com.archives.archives.service;

import com.archives.archives.dto.ManuscriptDTO;
import com.archives.archives.entity.Manuscript;
import com.archives.archives.dto.FolioDTO;
import com.archives.archives.dto.TagDTO;
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

        dto.setId(m.getId());
        dto.setTitle(m.getTitle());
        dto.setCote(m.getCote());
        dto.setTheme(m.getTheme());
        dto.setManuscriptName(m.getManuscriptName());
        dto.setDate(m.getDate());
        dto.setManufacturingPlace(m.getManufacturingPlace());
        dto.setConservationPlace(m.getConservationPlace());
        dto.setLink(m.getLink());
        dto.setSupport(m.getSupport());
        dto.setDimension(m.getDimension());

        if(m.getTags() != null) {
            dto.setTags(
                m.getTags()
                    .stream()
                    .map(tag -> {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO.setId(tag.getId());
                        tagDTO.setName(tag.getName());
                        return tagDTO;
                    })
                    .toList()
            );
        }

        if(m.getFolios() != null) {
            dto.setFolios(
                m.getFolios()
                    .stream()
                    .map(folio -> {
                        FolioDTO folioDTO = new FolioDTO();
                        folioDTO.setId(folio.getId());
                        folioDTO.setPage(folio.getPage());
                        folioDTO.setFolio(folio.getFolio());
                        folioDTO.setSectionName(folio.getSectionName());
                        folioDTO.setIlluminationPosition(folio.getIlluminationPosition());
                        folioDTO.setTranscription(folio.getTranscription());
                        folioDTO.setZoom(folio.getZoom());
                        folioDTO.setIlluminationType(folio.getIlluminationType());
                        folioDTO.setDescription(folio.getDescription());
                        return folioDTO;
                    })
                    .toList()
            );
        }

        return dto;
    }

    // Convertit un DTO en Entity pour l'enregistrement 
    // en base de données
    public Manuscript toEntity(ManuscriptDTO dto) {
        Manuscript m = new Manuscript();

        m.setTitle(dto.getTitle());
        m.setCote(dto.getCote());
        m.setTheme(dto.getTheme());
        m.setManuscriptName(dto.getManuscriptName());
        m.setDate(dto.getDate());
        m.setManufacturingPlace(dto.getManufacturingPlace());
        m.setConservationPlace(dto.getConservationPlace());
        m.setLink(dto.getLink());
        m.setSupport(dto.getSupport());
        m.setDimension(dto.getDimension());

        return m;
    }

}
