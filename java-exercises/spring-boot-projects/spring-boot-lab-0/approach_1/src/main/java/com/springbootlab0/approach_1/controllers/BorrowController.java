package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.Borrow;
import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.domain.User;
import com.springbootlab0.approach_1.services.BorrowService;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    LibraryMemberService libraryMemberService;
    @Autowired
    PublicationService publicationService;
    @Autowired
    BorrowService borrowService;


    @GetMapping("/")
    public String index(Model containerToView) {
        // Retrieve all borrows:
        containerToView.addAttribute("currentBorrows", borrowService.getAllBorrows());
        return "borrows/borrowsIndex";
    }

    @GetMapping("/create/")
    public String createBorrow(Model containerToView) {
        // Retrieve all library members and add them to the model
        containerToView.addAttribute("availableUsers", libraryMemberService.getAllLibraryMembers());
        // Retrieve all available publications
        containerToView.addAttribute("publications", publicationService.getAvailablePublications());
        return "borrows/emptyBorrowForm";
    }

    @GetMapping("/create/{userId}")
    public String createBorrow(@PathVariable("userId") String userId, Model containerToView, RedirectAttributes redirectAttributes) {
        // Retrieve the user information based on the received ID
        Optional<LibraryMember> borrowUser = libraryMemberService.findLibraryMemberById(userId);
        // Add the borrow user to the Model
        if (borrowUser.isPresent()) {
            containerToView.addAttribute("borrowUser", borrowUser.get());
            // Retrieve all available publications
            containerToView.addAttribute("publications", publicationService.getAvailablePublications());
            return "borrows/borrowForm";
        } else {
            redirectAttributes.addFlashAttribute("responseMessage", "User ID " + userId + " not found in database!");
            return "redirect:/library/publications";
        }
    }

    @PostMapping("/create/{userId}")
    public String createBorrow(@PathVariable("userId") String userId, @RequestParam("publicationsToBorrow") List<String> publicationsToBorrow, RedirectAttributes redirectAttributes) {
        // Initialize an empty list of borrows
        List<Borrow> createdBorrows = new ArrayList<>();
        // Retrieve the user information based on the received ID
        Optional<LibraryMember> borrowUser = libraryMemberService.findLibraryMemberById(userId);
        if (borrowUser.isPresent()) {
            // For each Publication ID, create a borrow
            for (String publicationId : publicationsToBorrow) {
                // Retrieve the publication
                Optional<Publication> publicationToBorrow = publicationService.findPublicationById(publicationId);
                if (publicationToBorrow.isPresent()) {
                    // Create the borrow
                    createdBorrows.add(borrowService.createBorrow(borrowUser.get(), publicationToBorrow.get()));
                }

            }
        }
        redirectAttributes.addFlashAttribute("createdBorrows", createdBorrows);
        // Return to the main page
        return "redirect:/borrows/create/" + userId;
    }

}
