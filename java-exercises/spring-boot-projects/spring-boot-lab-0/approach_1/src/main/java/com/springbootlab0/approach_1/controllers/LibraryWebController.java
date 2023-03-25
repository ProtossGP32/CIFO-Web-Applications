package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.services.AuthorService;
import com.springbootlab0.approach_1.services.LibraryMemberService;
import com.springbootlab0.approach_1.services.PublicationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/library")
public class LibraryWebController {

    private static final String RESPONSE_MESSAGE = "responseMessage";
    private static final String REDIRECT_MAIN = "redirect:main";
    private static final String IS_LOGGED_IN = "isLoggedIn";

    // Add some autowired references
    @Autowired
    PublicationService publicationService;
    @Autowired
    AuthorService authorService;
    @Autowired
    LibraryMemberService libraryMemberService;

    private final Set<String> sessionIds = Collections.synchronizedSet(new HashSet<>());

    @GetMapping(value = {"", "/", "/index"})
    public String index(HttpSession session) {
        if (session.getAttribute(IS_LOGGED_IN) == null || !(boolean) session.getAttribute(IS_LOGGED_IN)) {
            // Force the login page
            return "redirect:login";
        }
        // Go to the library main page
        return "redirect:/library/main";
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model modelToView) {
        // Set the session.isLoggedIn attribute to false
        session.setAttribute(IS_LOGGED_IN, false);
        modelToView.addAttribute("availableUsers", libraryMemberService.getAllUsers());
        modelToView.addAttribute("availableLibrarians", libraryMemberService.getAllLibrarians());
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("libraryMemberId") String libraryMemberId, HttpSession session, RedirectAttributes redirectAttributes) {
        Optional<LibraryMember> optionalLibraryMember = libraryMemberService.findLibraryMemberById(libraryMemberId);
        // Only accept login when the session doesn't exist in the sessions set
        if (optionalLibraryMember.isPresent() && !this.sessionIds.contains(session.getId())) {
            // Add the HTTP session id to the sessions set
            this.sessionIds.add(session.getId());
            // Set the login value to true
            session.setAttribute(IS_LOGGED_IN, true);
            // Save useful member attributes in the HTTP session
            session.setAttribute("memberId", libraryMemberId);
            session.setAttribute("memberClass", optionalLibraryMember.get().getClass().getSimpleName());
            session.setAttribute("memberName", optionalLibraryMember.get().getFirstName() + " " + optionalLibraryMember.get().getLastName());
        } else {
            redirectAttributes.addFlashAttribute(RESPONSE_MESSAGE, "Can't login as User " + libraryMemberId);
        }
        return "redirect:/library/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Restart the session attributes
        session.removeAttribute(IS_LOGGED_IN);
        session.removeAttribute("memberId");
        session.removeAttribute("memberClass");
        session.removeAttribute("memberName");
        // Remove the current session from the sessions set
        this.sessionIds.remove(session.getId());
        // Return to the main page and force the login again
        return "redirect:/library/";
    }

    @GetMapping(value = "/main")
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
    @GetMapping(value = "/clearPublications")
    public String clearPublicationsFromH2() {
        // Clear all the H2 Publications DB
        publicationService.deleteAll();
        // Redirect to the Publications page
        return REDIRECT_MAIN;
    }

    @GetMapping(value = "/clearAuthors")
    public String clearAuthorsFromH2() {
        // Clear all the H2 Authors DB
        authorService.deleteAll();
        // Redirect to the Publications page
        return REDIRECT_MAIN;
    }

    @GetMapping(value = "/clearLibraryMembers")
    public String clearLibraryMembersFromH2() {
        // Clear all the H2 LibraryMember DB
        libraryMemberService.deleteAll();
        // Redirect to the Publications page
        return REDIRECT_MAIN;
    }

    /**
     * Library Members Management request
     * @return redirect to libraryMembers page
     */
    @GetMapping(value = "/libraryMemberManagement")
    public String goToLibraryMemberManagement() {
        return "redirect:/libraryMembers/";
    }
}
