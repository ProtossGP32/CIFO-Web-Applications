package com.springbootlab0.approach_1.controllers;

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

    @RequestMapping(value = {"", "/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/main")
    public String getPublicationsWeb(Model containerToView) {
        // Compose the container with all the information that the mainMenu.html
        // requires on render time
        // 1. Retrieve all library members
        containerToView.addAttribute("libraryMembersFromController",
                libraryMemberService.getAllLibraryMembers());

        // 2. For testing purposes, retrieve only Users from Library Members
        containerToView.addAttribute("usersFromController",
                libraryMemberService.getAllUsers());

        return "mainMenu";
    }

    // Clear databases methods
    @RequestMapping(value = "/clearPublications")
    public String clearPublicationsFromH2() {
        // Clear all the H2 Publications DB
        publicationService.deleteAll();
        // Redirect to the Publications page
        return "redirect:main";
    }

    @RequestMapping(value = "/clearAuthors")
    public String clearAuthorsFromH2() {
        // Clear all the H2 Authors DB
        authorService.deleteAll();
        // Redirect to the Publications page
        return "redirect:main";
    }

    @RequestMapping(value = "/clearLibraryMembers")
    public String clearLibraryMembersFromH2() {
        // Clear all the H2 LibraryMember DB
        libraryMemberService.deleteAll();
        // Redirect to the Publications page
        return "redirect:main";
    }

    /**
     * Library Members Management request
     * @return redirect to libraryMembers page
     */
    @RequestMapping(value = "/libraryMemberManagement")
    public String goToLibraryMemberManagement() {
        return "redirect:/libraryMembers/";
    }
}
