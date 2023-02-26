package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PublicationRepository extends CrudRepository<Publication, String> {
    // Additional methods
    Optional<Publication> findPublicationByTitle(String title);
}