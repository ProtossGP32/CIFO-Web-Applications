package com.springbootlab0.approach_1.rest_controllers;

import com.springbootlab0.approach_1.domain.Author;
import com.springbootlab0.approach_1.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authors")
public class AuthorsRestController {
    @Autowired
    AuthorService authorService;

    @GetMapping("all")
    public Iterable<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

}
