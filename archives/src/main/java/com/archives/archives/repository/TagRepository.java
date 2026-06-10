package com.archives.archives.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.archives.archives.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);
    
}
