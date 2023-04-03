package com.springbootlab0.approach_1.restControllers;

import com.springbootlab0.approach_1.domain.Librarian;
import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.User;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /*
    @PostMapping(path="create/{memberType}", consumes = "application/JSON")
    public LibraryMember createUser(@PathVariable("memberType") String memberType, @RequestBody String newMember) {
        switch (memberType) {
            case "user":
                return createUser(User.createFromJson(newMember));
            default:
                return null;
        }
    }
    */

    // Read Library Members
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
