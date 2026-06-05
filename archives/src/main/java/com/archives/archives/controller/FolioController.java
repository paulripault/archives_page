package com.archives.archives.controller;

import com.archives.archives.entity.Folio;
import com.archives.archives.service.FolioService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folios")
public class FolioController {
    private final FolioService service;

    public FolioController(FolioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Folio> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Folio getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Folio create(@RequestBody Folio folio) {
        return service.create(folio);
    }

    @PutMapping("/{id}")
    public Folio update(@PathVariable Long id, @RequestBody Folio folio) {
        return service.update(id, folio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
