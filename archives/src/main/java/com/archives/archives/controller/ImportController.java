package com.archives.archives.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.archives.archives.entity.Folio;
import com.archives.archives.entity.Manuscript;
import com.archives.archives.entity.Person;
import com.archives.archives.entity.Place;
import com.archives.archives.entity.Tag;
import com.archives.archives.repository.FolioRepository;
import com.archives.archives.repository.ManuscriptRepository;
import com.archives.archives.repository.PersonRepository;
import com.archives.archives.repository.PlaceRepository;
import com.archives.archives.repository.TagRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@RestController
@RequestMapping("/imports")
public class ImportController {

    private static final int EXPECTED_COLUMN_COUNT = 25;

    private final ManuscriptRepository manuscriptRepository;
    private final PersonRepository personRepository;
    private final FolioRepository folioRepository;
    private final PlaceRepository placeRepository;
    private final TagRepository tagRepository;

    private Person getOrCreatePerson(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        String cleanName = name.trim().toLowerCase();

        return personRepository.findByName(cleanName)
                .orElseGet(() -> {
                    Person p = new Person();
                    p.setName(cleanName);
                    return personRepository.save(p);
                });
    }

    private Place getOrCreatePlace(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        String cleanName = name.trim();

        return placeRepository.findByName(cleanName)
                .orElseGet(() -> {
                    Place p = new Place();
                    p.setName(cleanName);
                    return placeRepository.save(p);
                });
    }

    private Tag getOrCreateTag(String name) {

        if (name == null || name.isBlank()) {
            return null;
        }

        String cleanName = name.trim();

        return tagRepository.findByName(cleanName)
                .orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(cleanName);
                    return tagRepository.save(tag);
                });
    }

    public ImportController(
            ManuscriptRepository manuscriptRepository,
            PersonRepository personRepository,
            FolioRepository folioRepository,
            PlaceRepository placeRepository,
            TagRepository tagRepository) {
        this.manuscriptRepository = manuscriptRepository;
        this.personRepository = personRepository;
        this.folioRepository = folioRepository;
        this.placeRepository = placeRepository;
        this.tagRepository = tagRepository;
    }

    @PostMapping("/csv")
    @Transactional
    public String uploadCsv(@RequestParam("file") MultipartFile file) throws IOException, CsvValidationException {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le fichier CSV est vide.");
        }

        int importedRows = 0;

        try (
                CSVReader reader = new CSVReader(
                        new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));) {

            String[] headers = reader.readNext();
            if (headers == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le fichier CSV ne contient pas d'en-tête.");
            }

            String[] columns;
            int rowNumber = 1;

            while ((columns = reader.readNext()) != null) {
                rowNumber++;
                validateRow(columns, rowNumber);

                // Gestion des personnes
                String authorName = getColumn(columns, 8);
                String illuminatorName = getColumn(columns, 9);
                String translatorName = getColumn(columns, 10);
                String recipientName = getColumn(columns, 11);

                // Gestion des lieux
                String manufacturingPlaceName = getColumn(columns, 5);
                String conservationPlaceName = getColumn(columns, 13);

                Place manufacturingPlace = getOrCreatePlace(manufacturingPlaceName);
                Place conservationPlace = getOrCreatePlace(conservationPlaceName);

                String title = getColumn(columns, 0);
                String cote = getColumn(columns, 1);
                String link = getColumn(columns, 24);
                Manuscript manuscript = link.isBlank()
                        ? manuscriptRepository.findFirstByTitleAndCoteOrderByIdAsc(title, cote)
                                .orElseGet(Manuscript::new)
                        : manuscriptRepository.findFirstByLinkOrderByIdAsc(link)
                                .orElseGet(Manuscript::new);

                // Enregistrement des manuscrits
                manuscript.setTitle(title);
                manuscript.setCote(cote);
                manuscript.setTheme(getColumn(columns, 2));
                manuscript.setManuscriptName(getColumn(columns, 3));

                manuscript.setSupport(getColumn(columns, 6));
                manuscript.setDimension(getColumn(columns, 7));

                manuscript.setDate(getColumn(columns, 12));
                manuscript.setLink(link);

                manuscript.setAuthor(getOrCreatePerson(authorName));
                manuscript.setTranslator(getOrCreatePerson(translatorName));
                manuscript.setIlluminator(getOrCreatePerson(illuminatorName));
                manuscript.setRecipient(getOrCreatePerson(recipientName));

                manuscript.setManufacturingPlace(manufacturingPlace);
                manuscript.setConservationPlace(conservationPlace);

                // Gestion des tags
                Tag person = getOrCreateTag(getColumn(columns, 21));
                if (person != null)
                    manuscript.getPersonTags().add(person);

                Tag place = getOrCreateTag(getColumn(columns, 22));
                if (place != null)
                    manuscript.getPlaceTags().add(place);

                Tag word = getOrCreateTag(getColumn(columns, 23));
                if (word != null)
                    manuscript.getWordTags().add(word);

                manuscript = manuscriptRepository.save(manuscript);

                String page = getColumn(columns, 4);
                String folioName = getColumn(columns, 16);
                Folio folio = folioRepository.findByManuscriptAndPageAndFolio(manuscript, page, folioName)
                        .orElseGet(Folio::new);

                // Enregistrement des folios
                folio.setPage(page);
                folio.setFolio(folioName);
                folio.setSectionName(getColumn(columns, 14));
                folio.setIlluminationPosition(getColumn(columns, 15));
                folio.setTranscription(getColumn(columns, 18));
                folio.setZoom(getColumn(columns, 17));
                folio.setIlluminationType(getColumn(columns, 19));
                folio.setDescription(getColumn(columns, 20));

                folio.setManuscript(manuscript);
                folioRepository.save(folio);
                importedRows++;
            }
        }
        return "Import terminé : " + importedRows + " ligne(s) traitée(s).";
    }

    private void validateRow(String[] columns, int rowNumber) {
        if (columns.length < EXPECTED_COLUMN_COUNT) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ligne " + rowNumber + " invalide : " + columns.length + " colonne(s) au lieu de "
                            + EXPECTED_COLUMN_COUNT + ".");
        }

        if (getColumn(columns, 0).isBlank() || getColumn(columns, 1).isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ligne " + rowNumber + " invalide : le titre et la cote sont obligatoires.");
        }
    }

    private String getColumn(String[] columns, int index) {
        if (index >= columns.length || columns[index] == null) {
            return "";
        }

        return columns[index].trim();
    }

}
