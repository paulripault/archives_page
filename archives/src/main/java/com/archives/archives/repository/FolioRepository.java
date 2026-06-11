package com.archives.archives.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.archives.archives.entity.Folio;
import com.archives.archives.entity.Manuscript;

public interface FolioRepository extends JpaRepository<Folio, Long> {

    Optional<Folio> findByManuscriptAndPageAndFolio(Manuscript manuscript, String page, String folio);

}
