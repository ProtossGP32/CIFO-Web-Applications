package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.Author;
import com.springbootlab0.approach_1.domain.Book;
import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.domain.User;
import com.springbootlab0.approach_1.services.AuthorService;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/publications")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = {"/", ""})
    public String index() {
        return "/publications/publicationsIndex";
    }

    @RequestMapping("/publicationForm")
    public String publicationForm(ModelMap containerToView) {
        containerToView.addAttribute("newBook", new Book());
        containerToView.addAttribute("authors", authorService.getAllAuthors());
        containerToView.addAttribute("responseMessage", null);
        return "publications/publicationForm";
    }

    @RequestMapping("/createPublication")
    public String createPublication(@ModelAttribute("newBook") Book newBook, ModelMap containerToView, BindingResult result) {
        // TODO: Logic to create any Publication, not just a Book
        // TODO: Check if the publication already exists before inserting it again
        if (result.hasErrors()) {
            containerToView.addAttribute("responseMessage", "Invalid book object! Review the fields");
        }
        else {
            publicationService.createPublication(newBook);
            containerToView.addAttribute("newBook", new Book());
            containerToView.addAttribute("authors", authorService.getAllAuthors());
            containerToView.addAttribute("responseMessage", "Book " + newBook.getTitle() + " from " + newBook.getAuthor().getFirstName() + " " + newBook.getAuthor().getLastName() + " saved.");
        }
        return "publications/publicationForm";
    }

    @RequestMapping("/updatePublication")
    public String updatePublication(@RequestParam("id") String id, ModelMap containerToView) {
        // Retrieve the publication based on the received ID
        Optional<Publication> publicationToUpdate =  publicationService.findPublicationById(id);
        if (publicationToUpdate.isPresent()) {
            containerToView.addAttribute("publicationToUpdate", publicationToUpdate.get());
            System.out.println("Available publication in DB: " + publicationToUpdate);
            containerToView.addAttribute("authors", authorService.getAllAuthors());
            containerToView.addAttribute("responseMessage", null);
            return "publications/updatePublication";
        }
        else return "notFound";
    }

    @PostMapping("/updatePublication/{id}")
    public String updatePublication(@PathVariable("id") String id, Optional<Book> updatedPublication, ModelMap containerToView) {
        // Retrieve the publication based on the received ID
        System.out.println("Received publication: " + updatedPublication);
        Optional<Publication> publicationToUpdate =  publicationService.findPublicationById(id);
        System.out.println("Available publication in DB: " + publicationToUpdate);
        // Check if the publication is already inserted
        if (publicationToUpdate.isPresent()) {
            publicationService.createPublication(updatedPublication.get());
            containerToView.addAttribute("publicationToUpdate", publicationToUpdate.get());
            containerToView.addAttribute("authors", authorService.getAllAuthors());
            containerToView.addAttribute("responseMessage", "Publication " + publicationToUpdate.get().getTitle() + " updated!");
            return "publications/updatePublication";
        }
        return "notFound";
    }



    @RequestMapping("/authorForm")
    public String authorForm(ModelMap containerToView) {
        containerToView.addAttribute("newAuthor", new Author());
        containerToView.addAttribute("responseMessage", null);
        return "publications/authorForm";
    }

    @RequestMapping("/createAuthor")
    public String createAuthor(@ModelAttribute("newAuthor") Author newAuthor, ModelMap containerToView, BindingResult result) {
        if (result.hasErrors()) {
            containerToView.addAttribute("responseMessage", "Invalid author object! Review the fields");
        }
        else {
            authorService.createAuthor(newAuthor);
            containerToView.addAttribute("newAuthor", new Author());
            containerToView.addAttribute("responseMessage", "Author " + newAuthor.getFirstName() + " " + newAuthor.getLastName() + " saved!");
        }
        return "publications/authorForm";
    }
}
