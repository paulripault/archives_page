package com.archives.archives.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.archives.archives.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
