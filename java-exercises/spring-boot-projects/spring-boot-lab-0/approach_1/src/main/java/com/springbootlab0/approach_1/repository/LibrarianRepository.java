package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Librarian;
import org.springframework.data.repository.CrudRepository;

public interface LibrarianRepository extends CrudRepository<Librarian, String> {
}