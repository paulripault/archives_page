package com.archives.archives.service;

import com.archives.archives.dto.ManuscriptDTO;
import com.archives.archives.dto.PlaceDTO;
import com.archives.archives.entity.Manuscript;
import com.archives.archives.entity.Place;
import com.archives.archives.entity.Tag;
import com.archives.archives.dto.FolioDTO;
import com.archives.archives.dto.TagDTO;
import com.archives.archives.repository.ManuscriptRepository;
import com.archives.archives.repository.PlaceRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManuscriptService {

    private final ManuscriptRepository repository;
    private final PlaceRepository placeRepository;

    private PlaceDTO toPlaceDTO(Place place) {
        if (place == null)
            return null;

        PlaceDTO dto = new PlaceDTO();
        dto.setId(place.getId());
        dto.setName(place.getName());

        return dto;
    }

    private List<TagDTO> mapTags(List<Tag> tags) {
    if (tags == null) return List.of();

    return tags.stream()
            .map(tag -> {
                TagDTO dto = new TagDTO();
                dto.setId(tag.getId());
                dto.setName(tag.getName());
                return dto;
            })
            .toList();
}

    public ManuscriptService(ManuscriptRepository repository, PlaceRepository placeRepository) {
        this.repository = repository;
        this.placeRepository = placeRepository;
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
        existing.setManufacturingPlace(toPlaceEntity(dto.getManufacturingPlace()));
        existing.setConservationPlace(toPlaceEntity(dto.getConservationPlace()));
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

    public Place toPlaceEntity(PlaceDTO placeDTO) {
        if (placeDTO == null)
            return null;

        // Si le DTO contient un ID,
        // on suppose que c'est une entité déjà existante
        if (placeDTO.getId() != null) {
            return placeRepository.findById(placeDTO.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Place not found with id: " + placeDTO.getId()));
        }

        // Sinon, on crée une nouvelle entité à partir du DTO
        Place place = new Place();
        place.setName(placeDTO.getName());
        return place;

    }

    public ManuscriptDTO toDTO(Manuscript m) {
        ManuscriptDTO dto = new ManuscriptDTO();

        dto.setId(m.getId());
        dto.setTitle(m.getTitle());
        dto.setCote(m.getCote());
        dto.setTheme(m.getTheme());
        dto.setManuscriptName(m.getManuscriptName());
        dto.setDate(m.getDate());
        dto.setLink(m.getLink());
        dto.setSupport(m.getSupport());
        dto.setDimension(m.getDimension());
        dto.setManufacturingPlace(toPlaceDTO(m.getManufacturingPlace()));
        dto.setConservationPlace(toPlaceDTO(m.getConservationPlace()));

        dto.setPersonTags(mapTags(m.getPersonTags()));
        dto.setPlaceTags(mapTags(m.getPlaceTags()));
        dto.setWordTags(mapTags(m.getWordTags()));


        if (m.getFolios() != null) {
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
                            .toList());
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
        m.setManufacturingPlace(toPlaceEntity(dto.getManufacturingPlace()));
        m.setConservationPlace(toPlaceEntity(dto.getConservationPlace()));
        m.setLink(dto.getLink());
        m.setSupport(dto.getSupport());
        m.setDimension(dto.getDimension());

        return m;
    }

}
