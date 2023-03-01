package com.springbootlab0.approach_1.services;

import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.User;
import com.springbootlab0.approach_1.repository.LibraryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryMemberService {
    @Autowired
    LibraryMemberRepository libraryMemberRepository;

    // CRUD
    public LibraryMember createLibraryMember(LibraryMember libraryMember) {
        return libraryMemberRepository.save(libraryMember);
    }

    public Optional<LibraryMember> findLibraryMemberById(String id) {
        return libraryMemberRepository.findById(id);
    }

    public void deleteLibraryMemberById(String id) {
        libraryMemberRepository.deleteById(id);
    }

    public LibraryMember updateLibraryMember(LibraryMember libraryMember) {
        return libraryMemberRepository.save(libraryMember);
    }

    public void deleteLibraryMember(LibraryMember libraryMember) {
        libraryMemberRepository.delete(libraryMember);
    }

    public void deleteAll() {
        libraryMemberRepository.deleteAll();
    }

    public Iterable<LibraryMember> getAllLibraryMembers() {
        return libraryMemberRepository.findAll();
    }

    public Iterable<User> getAllUsers() {
        return libraryMemberRepository.findUsers();
    }
}
