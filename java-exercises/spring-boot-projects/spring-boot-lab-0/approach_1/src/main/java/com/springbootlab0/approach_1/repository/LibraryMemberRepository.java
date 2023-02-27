package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.LibraryMember;
import org.springframework.data.repository.CrudRepository;

public interface LibraryMemberRepository extends CrudRepository<LibraryMember, String> {
}