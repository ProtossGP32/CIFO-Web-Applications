package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}