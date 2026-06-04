package com.archives.archives.service;

import com.archives.archives.entity.Manuscript;
import com.archives.archives.repository.ManuscriptRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManuscriptService {
    private final ManuscriptRepository repository;

    public ManuscriptService(ManuscriptRepository repository) {
        this.repository = repository;
    }

    public List<Manuscript> getAll() {
        return repository.findAll();
    }

    public Manuscript create(Manuscript manuscript) {
        return repository.save(manuscript);
    }
}
