package com.archives.archives.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.archives.archives.entity.Manuscript;

public interface ManuscriptRepository extends JpaRepository<Manuscript, Long> {
    @Query("SELECT m FROM Manuscript m LEFT JOIN FETCH m.tags LEFT JOIN FETCH m.folios")
    List<Manuscript> findAllWithRelations();
}
