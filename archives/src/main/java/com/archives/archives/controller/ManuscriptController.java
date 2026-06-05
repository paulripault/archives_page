package com.archives.archives.controller;

import com.archives.archives.dto.ManuscriptDTO;
import com.archives.archives.service.ManuscriptService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manuscripts")
public class ManuscriptController {

    private final ManuscriptService service;

    public ManuscriptController(ManuscriptService service) {
        this.service = service;
    }

    @GetMapping
    public List<ManuscriptDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ManuscriptDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ManuscriptDTO create(@RequestBody ManuscriptDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ManuscriptDTO update(@PathVariable Long id, @RequestBody ManuscriptDTO dto) {
        return service.update(id, dto);  
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
