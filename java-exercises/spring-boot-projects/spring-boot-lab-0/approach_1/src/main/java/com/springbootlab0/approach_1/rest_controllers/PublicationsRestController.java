package com.springbootlab0.approach_1.rest_controllers;

import com.springbootlab0.approach_1.domain.*;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/publications")
public class PublicationsRestController {

    @Autowired
    PublicationService publicationService;

    @GetMapping("all")
    public Iterable<Publication> getAllPublications() {
        return publicationService.getAllPublications();
    }

    @GetMapping("books")
    public Iterable<Publication> getAllBooks() {
        return publicationService.getAllBooks();
    }

    @PostMapping(value = "books/create", consumes = "application/JSON")
    public Publication createBook(@RequestBody Book book) {
        return publicationService.createPublication(book);
    }

    @GetMapping("cds")
    public Iterable<Publication> getAllCD() {
        return publicationService.getAllCDs();
    }

    @PostMapping(value = "cds/create", consumes = "application/JSON")
    public Publication createCD(@RequestBody CD cd) {
        return publicationService.createPublication(cd);
    }

    @GetMapping("dvds")
    public Iterable<Publication> getAllDVD() {
        return publicationService.getAllDVDs();
    }

    @PostMapping(value = "dvds/create", consumes = "application/JSON")
    public Publication createDVD(@RequestBody DVD dvd) {
        return publicationService.createPublication(dvd);
    }

}
