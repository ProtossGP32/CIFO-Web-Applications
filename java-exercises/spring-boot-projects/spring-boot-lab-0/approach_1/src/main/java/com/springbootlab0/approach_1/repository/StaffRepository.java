package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, String> {
}