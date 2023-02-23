package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}