package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.User;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libraryMembers")
public class LibraryMemberController {
    @Autowired
    LibraryMemberService libraryMemberService;


    @RequestMapping("/")
    public String index() {
        return "libraryMembers/libraryMembersIndex";
    }

    /**
     * TODO: Avoid creating a User object in this method or any other LibraryMember subclass object
     * With pure HTML here we wouldn't need to previously specify an object to fill
     */
    @GetMapping(value = "/createUser")
    public String memberForm(Model userModel) {
        // Instantiate a new User object
        // TODO: the Member form should initialize a LibraryMember-like object, not a User
        userModel.addAttribute("newUser", new User());
        // In GET methods, we invoke the same method
        return "libraryMembers/createUser";
    }

    @PostMapping("/createUser")
    public String userSubmit(@ModelAttribute("newUser") User newUser, Model containerToView) {

        // Insert the new User to the database
        libraryMemberService.createLibraryMember(newUser);
        // TODO: Add logic to know if the received LibraryMember is a User, a Librarian or a Staff member
        // Add the newUser again to the containerToView
        containerToView.addAttribute("newUser", newUser);

        // Return to the createUser page
        return "libraryMembers/createUser";
    }

    @RequestMapping("/memberForm")
    public String memberForm() {
        return "libraryMembers/createMember";
    }

    @RequestMapping("/createMember")
    public String createMember(@ModelAttribute("newMember") User newMember, BindingResult result) {
        // TODO: Logic to create any LibraryMember, not just a User
        if (result.hasErrors()) {
            System.out.println(result);
        }
        libraryMemberService.createLibraryMember(newMember);
        return "libraryMembers/createMember";
    }
}
