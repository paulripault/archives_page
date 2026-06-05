package com.archives.archives.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.archives.archives.entity.Folio;

public interface FolioRepository extends JpaRepository<Folio, Long> {
    
}
