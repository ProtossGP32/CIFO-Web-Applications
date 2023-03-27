package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.domain.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, String> {
    // Additional methods
    Optional<Publication> findPublicationByTitle(String title);
    Iterable<Publication> findByStatus(Status status);

    @Query("from Book")
    Iterable<Publication> findBooks();
    @Query("from CD")
    Iterable<Publication> findCDs();
    @Query("from DVD")
    Iterable<Publication> findDVDs();
}