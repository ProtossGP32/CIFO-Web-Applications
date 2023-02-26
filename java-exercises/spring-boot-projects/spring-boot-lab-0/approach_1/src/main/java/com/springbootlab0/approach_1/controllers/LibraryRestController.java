package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.*;
import com.springbootlab0.approach_1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LibraryRestController {

    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CDRepository cdRepository;
    @Autowired
    DVDRepository dvdRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LibrarianRepository librarianRepository;

    @GetMapping("publications")
    public Iterable<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @GetMapping("books")
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("cd")
    public Iterable<CD> getAllCD() {
        return cdRepository.findAll();
    }

    @GetMapping("dvd")
    public Iterable<DVD> getAllDVD() {
        return dvdRepository.findAll();
    }

    @GetMapping("librarians")
    public Iterable<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    @GetMapping("users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }


}
