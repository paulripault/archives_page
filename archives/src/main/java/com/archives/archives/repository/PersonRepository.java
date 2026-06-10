package com.archives.archives.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.archives.archives.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    Optional<Person> findByName(String name);

}
