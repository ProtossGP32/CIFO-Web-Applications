package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Borrow;
import org.springframework.data.repository.CrudRepository;

public interface BorrowRepository extends CrudRepository<Borrow, String> {
}