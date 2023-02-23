package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.DVD;
import org.springframework.data.repository.CrudRepository;

public interface DVDRepository extends CrudRepository<DVD, String> {
}