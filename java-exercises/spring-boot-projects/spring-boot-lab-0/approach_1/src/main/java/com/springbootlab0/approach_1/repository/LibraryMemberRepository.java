package com.springbootlab0.approach_1.repository;

import com.springbootlab0.approach_1.domain.Librarian;
import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryMemberRepository extends CrudRepository<LibraryMember, String> {
    @Query("from User")
    Iterable<LibraryMember> findUsers();

    @Query("from Librarian")
    Iterable<LibraryMember> findLibrarians();
}