package com.archives.archives.controller;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

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

    private final ManuscriptRepository manuscriptRepository;
    private final PersonRepository personRepository;
    private final FolioRepository folioRepository;
    private final PlaceRepository placeRepository;
    private final TagRepository tagRepository;

    private Person getOrCreatePerson(String name) {
        return personRepository.findByName(name)
                .orElseGet(() -> {
                    Person p = new Person();
                    p.setName(name);
                    return personRepository.save(p);
                });
    }

    private Place getOrCreatePlace(String name) {
        return placeRepository.findByName(name)
                .orElseGet(() -> {
                    Place p = new Place();
                    p.setName(name);
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

        try (
                CSVReader reader = new CSVReader(
                        new InputStreamReader(file.getInputStream()));) {

            reader.readNext();

            String[] columns;

            while ((columns = reader.readNext()) != null) {
                System.out.println(columns.length);

                // Gestion des personnes
                String authorName = columns[8] == null ? "" : columns[8].trim().toLowerCase();
                String translatorName = columns[9] == null ? "" : columns[9].trim().toLowerCase();
                String illuminatorName = columns[10] == null ? "" : columns[10].trim().toLowerCase();
                String recipientName = columns[11] == null ? "" : columns[11].trim().toLowerCase();

                // Gestion des lieux
                String manufacturingPlaceName = columns[5] == null ? "" : columns[5].trim();
                String conservationPlaceName = columns[13] == null ? "" : columns[13].trim();

                Place manufacturingPlace = getOrCreatePlace(manufacturingPlaceName);
                Place conservationPlace = getOrCreatePlace(conservationPlaceName);

                String cote = columns[1].trim();
                Manuscript manuscript = manuscriptRepository.findFirstByCoteOrderByIdAsc(cote)
                        .orElseGet(Manuscript::new);

                // Enregistrement des manuscrits
                manuscript.setTitle(columns[0].trim());
                manuscript.setCote(cote);
                manuscript.setTheme(columns[2].trim());
                manuscript.setManuscriptName(columns[3].trim());

                manuscript.setSupport(columns[6].trim());
                manuscript.setDimension(columns[7].trim());

                manuscript.setDate(columns[12].trim());
                manuscript.setLink(columns[24].trim());

                if (!authorName.isBlank()) {
                    manuscript.setAuthor(getOrCreatePerson(authorName));
                }
                if (!translatorName.isBlank()) {
                    manuscript.setTranslator(getOrCreatePerson(translatorName));
                }
                if (!illuminatorName.isBlank()) {
                    manuscript.setIlluminator(getOrCreatePerson(illuminatorName));
                }
                if (!recipientName.isBlank()) {
                    manuscript.setRecipient(getOrCreatePerson(recipientName));
                }

                manuscript.setManufacturingPlace(manufacturingPlace);
                manuscript.setConservationPlace(conservationPlace);

                // Gestion des tags
                Tag person = getOrCreateTag(columns[21]);
                if (person != null)
                    manuscript.getPersonTags().add(person);

                Tag place = getOrCreateTag(columns[22]);
                if (place != null)
                    manuscript.getPlaceTags().add(place);

                Tag word = getOrCreateTag(columns[23]);
                if (word != null)
                    manuscript.getWordTags().add(word);

                manuscript = manuscriptRepository.save(manuscript);

                String page = columns[4].trim();
                String folioName = columns[16].trim();
                Folio folio = folioRepository.findByManuscriptAndPageAndFolio(manuscript, page, folioName)
                        .orElseGet(Folio::new);

                // Enregistrement des folios
                folio.setPage(columns[4]);
                folio.setFolio(columns[16]);
                folio.setSectionName(columns[14]);
                folio.setIlluminationPosition(columns[15]);
                folio.setTranscription(columns[18]);
                folio.setZoom(columns[17]);
                folio.setIlluminationType(columns[19]);
                folio.setDescription(columns[20]);

                folio.setManuscript(manuscript);
                folioRepository.save(folio);

                System.out.println(manuscript);
                System.out.println(folio);
            }
        }
        return "Ok";
    }

}
