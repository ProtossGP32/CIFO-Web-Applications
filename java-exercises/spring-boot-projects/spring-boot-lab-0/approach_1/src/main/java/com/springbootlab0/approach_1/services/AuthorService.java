package com.springbootlab0.approach_1.services;

import com.springbootlab0.approach_1.domain.Author;
import com.springbootlab0.approach_1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    // CRUD
    public Author createAuthor(Author author) {
        Author authorCreated = authorRepository.save(author);
        return authorCreated;
    }

    public Optional<Author> findAuthorById(String id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthorById(String id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(Author author) {
        // Same procedure as createAuthor
        return authorRepository.save(author);
    }

    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    public void deleteAll() {
        authorRepository.deleteAll();
    }

}
