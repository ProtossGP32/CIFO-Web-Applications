package com.springbootlab0.approach_1.restControllers;

import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/librarymembers")
public class LibraryMembersRestController {
    @Autowired
    LibraryMemberService libraryMemberService;

    @GetMapping("all")
    public Iterable<LibraryMember> getAllLibraryMembers() {
        return libraryMemberService.getAllLibraryMembers();
    }

    @GetMapping("librarians")
    public Iterable<LibraryMember> getAllLibrarians() {
        return libraryMemberService.getAllLibrarians();
    }

    @GetMapping("users")
    public Iterable<LibraryMember> getAllUsers() {
        return libraryMemberService.getAllUsers();
    }
}
