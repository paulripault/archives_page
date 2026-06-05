package com.archives.archives.controller;

import com.archives.archives.dto.FolioDTO;
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
    public List<FolioDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public FolioDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public FolioDTO create(@RequestBody FolioDTO folio) {
        return service.create(folio);
    }

    @PutMapping("/{id}")
    public FolioDTO update(@PathVariable Long id, @RequestBody FolioDTO folio) {
        return service.update(id, folio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
