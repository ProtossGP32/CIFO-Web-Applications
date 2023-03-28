package com.springbootlab0.approach_1.restControllers;

import com.springbootlab0.approach_1.domain.*;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("books/create")
    public Publication createBook(Book book) {
        return publicationService.createPublication(book);
    }

    @GetMapping("cd")
    public Iterable<Publication> getAllCD() {
        return publicationService.getAllCDs();
    }

    @PostMapping("cd/create")
    public Publication createCD(CD cd) {
        return publicationService.createPublication(cd);
    }



    @GetMapping("dvd")
    public Iterable<Publication> getAllDVD() {
        return publicationService.getAllDVDs();
    }

    @PostMapping("dvd/create")
    public Publication createDVD(DVD dvd) {
        return publicationService.createPublication(dvd);
    }

}
