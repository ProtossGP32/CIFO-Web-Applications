package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Librarian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LibrarianRepository<T extends Librarian> extends CrudRepository<T, String> {
}