package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.Author;
import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.User;
import com.springbootlab0.approach_1.services.DemoService;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/libraryMembers")
public class LibraryMemberController {
    @Autowired
    LibraryMemberService libraryMemberService;

    // Demo service - Only for demo purposes!
    @Autowired
    DemoService demoService;

    @RequestMapping({"/", ""})
    public String index(Model containerToView) {
        // 1. Retrieve all library members
        containerToView.addAttribute("libraryMembersFromController",
                libraryMemberService.getAllLibraryMembers());

        // 2. For testing purposes, retrieve only Users from Library Members
        containerToView.addAttribute("usersFromController",
                libraryMemberService.getAllUsers());
        // 3. Add a response message
        containerToView.addAttribute("responseMessage", null);
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
        containerToView.addAttribute("newUser", new User());
        // Return to the createUser page
        return "libraryMembers/createUser";
    }

    @RequestMapping("/memberForm")
    public String memberForm(ModelMap containerToView) {
        containerToView.addAttribute("newMember", new User());
        containerToView.addAttribute("responseMessage", null);
        return "libraryMembers/memberForm";
    }

    @RequestMapping("/createMember")
    public String createMember(@ModelAttribute("newMember") User newMember, ModelMap containerToView, BindingResult result) {
        // TODO: Logic to create any LibraryMember, not just a User
        if (result.hasErrors()) {
            containerToView.addAttribute("responseMessage", "Invalid author object! Review the fields");
        }
        else {
            libraryMemberService.createLibraryMember(newMember);
            containerToView.addAttribute("newUser", new User());
            containerToView.addAttribute("responseMessage", "User " + newMember.getFirstName() + " " + newMember.getLastName() + " saved!");
        }

        return "libraryMembers/memberForm";
    }

    /**
     * Demo purposes: Create the number of expected Library Members
     * and insert them into the DB
     */
    @RequestMapping(value = "/createFakeLibraryMembers")
    public String createFakeLibraryMembers(@RequestParam("qtyMembers") int qty, RedirectAttributes redirectAttributes) {
        // Call the faker generator
        demoService.createFakeLibraryMembers(qty);
        redirectAttributes.addFlashAttribute("responseMessage", "Added 10 new Library Members");
        // Redirect to the Library Members main page
        return "redirect:";
    }
}
