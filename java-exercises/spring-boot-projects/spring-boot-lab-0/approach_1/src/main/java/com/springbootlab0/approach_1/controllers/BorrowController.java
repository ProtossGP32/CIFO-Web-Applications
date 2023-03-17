package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.services.BorrowService;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    LibraryMemberService libraryMemberService;
    @Autowired
    PublicationService publicationService;
    @Autowired
    BorrowService borrowService;


    @GetMapping({"/",""})
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
        return "borrows/borrowForm";
    }

    @GetMapping("/create/{userId}")
    public String createBorrow(@PathVariable("userId") String userId, Model containerToView, RedirectAttributes redirectAttributes) {
        // Retrieve the user information based on the received ID
        if (libraryMemberService.findLibraryMemberById(userId).isPresent()) {
            containerToView.addAttribute("pathId", userId);
            // Retrieve all available publications
            containerToView.addAttribute("publications", publicationService.getAvailablePublications());
            return "borrows/borrowForm";
        } else {
            redirectAttributes.addFlashAttribute("responseMessage", "User ID " + userId + " not found in database!");
            return "redirect:/library/publications";
        }
    }

    @PostMapping({ "/create/", "/create/{pathId}"})
    public String createBorrow(@PathVariable(value = "pathId", required = false) String pathId, @RequestParam(value = "userId", required = false) String userId, @RequestParam("publicationsToBorrow") List<String> publicationsToBorrow, RedirectAttributes redirectAttributes) {
        // If neither one of the ID exist, return an error
        if (pathId == null && userId == null) {
            redirectAttributes.addFlashAttribute(
                    "responseMessage",
                    "Something wrong with User IDs on Borrow creation time"
            );
            // Return to the Borrows main page
            return "redirect:/borrows/";
        }

        // Assign the pathId value to the userId variable
        if (pathId != null) {
            userId = pathId;
        }

        // Create a borrow for each received publication ID and save them as a flash attribute
        redirectAttributes.addFlashAttribute(
                "createdBorrows",
                borrowService.createMultipleBorrows(userId, publicationsToBorrow));

        // Redirect to the generic borrow creator
        if (pathId == null) {
            return "redirect:/borrows/create/";
        }

        // Redirect with the userId to continue creating borrows for that user
        return "redirect:/borrows/create/" + userId;
    }

    @GetMapping("/delete/{id}")
    public String deleteBorrow(@PathVariable(value = "id") String borrowId, RedirectAttributes redirectAttributes) {
        if (borrowService.deleteBorrowByID(borrowId)) {
            redirectAttributes.addFlashAttribute("responseMessage", "Borrow ID " + borrowId + " deleted!");
        } else {
            redirectAttributes.addFlashAttribute("responseMessage", "Borrow ID " + borrowId + " not found!");
        }
        return "redirect:/borrows";
    }
}
