package com.springbootlab0.approach_1.restControllers;

import com.springbootlab0.approach_1.domain.*;
import com.springbootlab0.approach_1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/publications")
public class PublicationsRestController {

    @Autowired
    PublicationRepository publicationRepository;

    @GetMapping("all")
    public Iterable<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @GetMapping("books")
    public Iterable<Publication> getAllBooks() {
        return publicationRepository.findBooks();
    }

    @GetMapping("cd")
    public Iterable<Publication> getAllCD() {
        return publicationRepository.findCDs();
    }

    @GetMapping("dvd")
    public Iterable<Publication> getAllDVD() {
        return publicationRepository.findDVDs();
    }

}
