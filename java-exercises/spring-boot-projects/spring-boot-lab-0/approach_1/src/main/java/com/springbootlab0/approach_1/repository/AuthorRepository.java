package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuthorRepository<T extends Author> extends CrudRepository<T, String> {
}