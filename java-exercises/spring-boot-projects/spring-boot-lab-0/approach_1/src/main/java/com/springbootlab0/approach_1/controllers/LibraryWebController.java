package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.User;
import com.springbootlab0.approach_1.repository.UserRepository;
import com.springbootlab0.approach_1.services.AuthorService;
import com.springbootlab0.approach_1.services.DemoService;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class LibraryWebController {

    // Add some autowired references
    @Autowired
    PublicationService publicationService;
    @Autowired
    AuthorService authorService;
    @Autowired
    LibraryMemberService libraryMemberService;

    // Demo service - Only for demo purposes!
    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/publications")
    public String getPublicationsWeb(Model containerToView) {
        // Compose the container with all the information that the showPublications.html
        // requires on render time
        // 1. Retrieve all publications
        containerToView.addAttribute("publicationsFromController",
                publicationService.getAllPublications());
        // 2. Retrieve all library members
        containerToView.addAttribute("libraryMembersFromController",
                libraryMemberService.getAllLibraryMembers());

        return "showPublications";
    }

    // Clear databases methods
    @RequestMapping(value = "/clearPublications")
    public String clearPublicationsFromH2() {
        // Clear all the H2 Publications DB
        publicationService.deleteAll();
        // Redirect to the Publications page
        return "redirect:publications";
    }

    @RequestMapping(value = "/clearAuthors")
    public String clearAuthorsFromH2() {
        // Clear all the H2 Authors DB
        authorService.deleteAll();
        // Redirect to the Publications page
        return "redirect:publications";
    }

    @RequestMapping(value = "/clearLibraryMembers")
    public String clearLibraryMembersFromH2() {
        // Clear all the H2 LibraryMember DB
        libraryMemberService.deleteAll();
        // Redirect to the Publications page
        return "redirect:publications";
    }

    // Entities creation
    /**
     *
     */
    @GetMapping(value = "/createUser")
    public String memberForm(Model userModel) {
        // Instantiate a new User object
        userModel.addAttribute("newUser", new User());
        // In GET methods, we invoke the same method
        return "createUser";
    }

    @PostMapping("/createUser")
    public String userSubmit(@ModelAttribute User newUser, Model containerToView) {

        // Insert the new User to the database
        libraryMemberService.createLibraryMember(newUser);
        // TODO: Add logic to know if the received LibraryMember is a User, a Librarian or a Staff member
        // Add the newUser again to the containerToView
        containerToView.addAttribute("newUser", newUser);

        // Return to the createUser page
        return "createUser";
    }


    /**
     * Demo purposes: Create the number of expected publications
     * and insert them into the DB
     */
    @RequestMapping(value = "/createFakePublications")
    public String createFakePublications(@RequestParam("qtyPublications") int qty) {
        // Call the faker generator
        demoService.createFakePublications(qty);
        // Redirect to the Publications page
        return "redirect:publications";
    }

    /**
     * Demo purposes: Create the number of expected Library Members
     * and insert them into the DB
     */
    @RequestMapping(value = "/createFakeLibraryMembers")
    public String createFakeLibraryMembers(@RequestParam("qtyMembers") int qty) {
        // Call the faker generator
        demoService.createFakeLibraryMembers(qty);
        // Redirect to the Publications page
        return "redirect:publications";
    }
}
