package com.archives.archives.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.archives.archives.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByName(String name);

}
