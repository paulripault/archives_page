package com.archives.archives.controller;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.archives.archives.entity.Manuscript;
import com.archives.archives.repository.ManuscriptRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@RestController
@RequestMapping("/imports")
public class ImportController {

    private final ManuscriptRepository manuscriptRepository;

    public ImportController(ManuscriptRepository manuscriptRepository) {
        this.manuscriptRepository = manuscriptRepository;
    }

    @PostMapping("/csv")
    public String uploadCsv(@RequestParam("file") MultipartFile file) throws IOException, CsvValidationException {

        try (
            CSVReader reader = new CSVReader(
                    new InputStreamReader(file.getInputStream()));
        ) {
            
            reader.readNext();

            String[] columns;

            while ((columns = reader.readNext()) != null) {
                System.out.println(columns.length);

                Manuscript manuscript = new Manuscript();

                manuscript.setTitle(columns[0].trim());
                manuscript.setCote(columns[1].trim());
                manuscript.setTheme(columns[2].trim());
                manuscript.setManuscriptName(columns[3].trim());

                manuscript.setSupport(columns[6].trim());
                manuscript.setDimension(columns[7].trim());

                manuscript.setDate(columns[12].trim());

                manuscript.setLink(columns[24].trim());

                manuscriptRepository.save(manuscript);

                System.out.println(manuscript);
            }
        }
        return "Ok";
    }

}
