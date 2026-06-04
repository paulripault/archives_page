package com.archives.archives.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.archives.archives.entity.Manuscript;

public interface ManuscriptRepository extends JpaRepository<Manuscript, Long> {
}
