package com.springbootlab0.approach_1.services;

import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.domain.Status;
import com.springbootlab0.approach_1.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicationService {
    @Autowired
    PublicationRepository publicationRepository;

    public Iterable<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public Iterable<Publication> getAvailablePublications() {
        return publicationRepository.findByStatus(Status.AVAILABLE);
    }

    public Iterable<Publication> getAllBooks() {
        return publicationRepository.findBooks();
    }

    public Iterable<Publication> getAllCDs() {
        return publicationRepository.findCDs();
    }

    public Iterable<Publication> getAllDVDs() {
        return publicationRepository.findDVDs();
    }

    // CRUD
    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    public Optional<Publication> findPublicationById(String id) {
        return publicationRepository.findById(id);
    }

    public Optional<Publication> findPublicationByTitle(String title) {
        return publicationRepository.findPublicationByTitle(title);
    }

    public void deletePublicationById(String id) {
        publicationRepository.deleteById(id);
    }

    public Publication updatePublication(Publication publication) {
        // Same procedure as in createPublication
        return publicationRepository.save(publication);
    }

    public void deletePublication(Publication publication) {
        publicationRepository.delete(publication);
    }

    public void deleteAll() {
        publicationRepository.deleteAll();
    }


}
