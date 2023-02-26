package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, String> {
}