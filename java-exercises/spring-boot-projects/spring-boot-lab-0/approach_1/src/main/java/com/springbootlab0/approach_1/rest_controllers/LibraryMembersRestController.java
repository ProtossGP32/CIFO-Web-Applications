package com.springbootlab0.approach_1.rest_controllers;

import com.springbootlab0.approach_1.domain.Librarian;
import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.User;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import com.springbootlab0.approach_1.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/librarymembers")
public class LibraryMembersRestController {
    @Autowired
    LibraryMemberService libraryMemberService;

    // CRUD
    // Create a User
    @PostMapping(path="users/create", consumes = "application/JSON")
    public LibraryMember createUser(@RequestBody User newUser) {
        return libraryMemberService.createLibraryMember(newUser);
    }

    // Create a Librarian
    @PostMapping(path="librarians/create", consumes = "application/JSON")
    public LibraryMember createLibrarian(@RequestBody Librarian newLibrarian) {
        return libraryMemberService.createLibraryMember(newLibrarian);
    }


    @PostMapping(path="create/{memberType}", consumes = "application/JSON")
    public LibraryMember createUser(@PathVariable("memberType") String memberType, @RequestBody String newMember) {
        // TODO: migrate logic to LibraryMember service
        switch (memberType) {
            case "user": {
                return libraryMemberService.createLibraryMember(Helper.createFromJson(newMember, User.class));
            }
            case "librarian": {
                return libraryMemberService.createLibraryMember(Helper.createFromJson(newMember, Librarian.class));
            }
            default:
                return null;
        }
    }

    // Read Library Members
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("all")
    public Iterable<LibraryMember> getAllLibraryMembers() {
        return libraryMemberService.getAllLibraryMembers();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("librarians")
    public Iterable<LibraryMember> getAllLibrarians() {
        return libraryMemberService.getAllLibrarians();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("users")
    public Iterable<LibraryMember> getAllUsers() {
        return libraryMemberService.getAllUsers();
    }

    /*
    @DeleteMapping(path="users/delete", consumes = "application/JSON")
    public ResponseEntity<LibraryMember> deleteUser(@RequestBody String deleteBody) {

    }
     */
}
