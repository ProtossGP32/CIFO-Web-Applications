package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.*;
import com.springbootlab0.approach_1.services.AuthorService;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // TODO: Unify all methods that access the same forms (i.e: Publication, Author, etc...)
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

    /**
     * Retrieve the publication object based on the provided ID and shows the corresponding publication
     * form ready to update it.
     * @param id is the publication id to update
     * @param containerToView contains the attributes Thymeleaf requires to render the HTML template
     * @return the HTML path of the publication form
     */
    @GetMapping("/updatePublication/{id}")
    public String updatePublication(@PathVariable("id") String id, ModelMap containerToView) {
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

    /**
     * Given an updated publication with an ID, rewrite it in the DB if it is already present
     * @param id is the publication id to update
     * @param updatedPublication is the Publication object with the updated fields
     * @param containerToView contains the attributes Thymeleaf requires to render the HTML template
     * @return the HTML path of the publication form
     */
    @PostMapping("/updatePublication/Book/{id}")
    public String updateBook(@PathVariable("id") String id, Optional<Book> updatedPublication, ModelMap containerToView) {
        // Retrieve the publication based on the received ID
        System.out.println("Received publication: " + updatedPublication);
        Optional<Publication> publicationToUpdate =  publicationService.findPublicationById(id);
        System.out.println("Available publication in DB: " + publicationToUpdate);
        // Check if the Book is already inserted
        if (publicationToUpdate.isPresent()) {
            publicationService.createPublication(updatedPublication.get());
            containerToView.addAttribute("publicationToUpdate", publicationToUpdate.get());
            containerToView.addAttribute("authors", authorService.getAllAuthors());
            containerToView.addAttribute("responseMessage", "Book " + publicationToUpdate.get().getTitle() + " updated!");
            return "publications/updatePublication";
        }
        return "notFound";
    }

    @PostMapping("/updatePublication/CD/{id}")
    public String updateCD(@PathVariable("id") String id, Optional<CD> updatedPublication, ModelMap containerToView) {
        // Retrieve the publication based on the received ID
        System.out.println("Received publication: " + updatedPublication);
        Optional<Publication> publicationToUpdate =  publicationService.findPublicationById(id);
        System.out.println("Available publication in DB: " + publicationToUpdate);
        // Check if the CD is already inserted
        if (publicationToUpdate.isPresent()) {
            publicationService.createPublication(updatedPublication.get());
            containerToView.addAttribute("publicationToUpdate", publicationToUpdate.get());
            containerToView.addAttribute("authors", authorService.getAllAuthors());
            containerToView.addAttribute("responseMessage", "CD " + publicationToUpdate.get().getTitle() + " updated!");
            return "publications/updatePublication";
        }
        return "notFound";
    }

    @PostMapping("/updatePublication/DVD/{id}")
    public String updateDVD(@PathVariable("id") String id, Optional<DVD> updatedPublication, ModelMap containerToView) {
        // Retrieve the publication based on the received ID
        System.out.println("Received publication: " + updatedPublication);
        Optional<Publication> publicationToUpdate =  publicationService.findPublicationById(id);
        System.out.println("Available publication in DB: " + publicationToUpdate);
        // Check if the DVD is already inserted
        if (publicationToUpdate.isPresent()) {
            publicationService.createPublication(updatedPublication.get());
            containerToView.addAttribute("publicationToUpdate", publicationToUpdate.get());
            containerToView.addAttribute("authors", authorService.getAllAuthors());
            containerToView.addAttribute("responseMessage", "DVD " + publicationToUpdate.get().getTitle() + " updated!");
            return "publications/updatePublication";
        }
        return "notFound";
    }

    /**
     * Given a Publication ID, delete that entry from the DB
     * @param id is the Publication id to delete
     * @param redirectAttrs contains the attributes Thymeleaf requires to render on redirection time
     * @return a redirection to the main publications HTML page
     */
    @GetMapping("/deletePublication/{id}")
    public String deletePublication(@PathVariable("id") String id, RedirectAttributes redirectAttrs) {
        // Retrieve the publication based on the received ID
        Optional<Publication> publicationToDelete =  publicationService.findPublicationById(id);
        if (publicationToDelete.isPresent()) {
            publicationService.deletePublicationById(id);
            redirectAttrs.addFlashAttribute("responseMessage", "Publication " + publicationToDelete.get().getTitle() + " deleted");
            return "redirect:/library/publications";
        }
        else return "notFound";
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
