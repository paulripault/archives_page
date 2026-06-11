package com.archives.archives.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.archives.archives.entity.Manuscript;

public interface ManuscriptRepository extends JpaRepository<Manuscript, Long> {

    Optional<Manuscript> findFirstByCoteOrderByIdAsc(String cote);

    @Query("SELECT m FROM Manuscript m")
    @EntityGraph(attributePaths = {
        "folios",
        "personTags",
        "placeTags",
        "wordTags",
        "author",
        "translator",
        "illuminator",
        "recipient",
        "manufacturingPlace",
        "conservationPlace"
    })
    List<Manuscript> findAllWithRelations();
}
