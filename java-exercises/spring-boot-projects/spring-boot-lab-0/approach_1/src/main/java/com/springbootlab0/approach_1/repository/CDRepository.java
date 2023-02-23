package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.CD;
import org.springframework.data.repository.CrudRepository;

public interface CDRepository extends CrudRepository<CD, String> {
}