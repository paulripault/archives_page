package com.archives.archives.controller;

import com.archives.archives.entity.Manuscript;
import com.archives.archives.service.ManuscriptService;
import org.springframework.web.bind.annotation.*;

//import java.util.List;

@RestController
@RequestMapping("/manuscripts")
public class ManuscriptController {
    private final ManuscriptService service;

    public ManuscriptController(ManuscriptService service) {
        this.service = service;
    }
/*
    @GetMapping
    public List<Manuscript> getAll() {
        return service.getAll();
    }
*/

    @PostMapping
    public Manuscript create(@RequestBody Manuscript manuscript) {
        System.out.println("TITLE = " + manuscript.getTitle());
        return service.create(manuscript);
    }

}
