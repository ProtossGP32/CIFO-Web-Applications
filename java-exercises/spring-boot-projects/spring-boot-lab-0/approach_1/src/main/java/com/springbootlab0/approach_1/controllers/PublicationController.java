package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.*;
import com.springbootlab0.approach_1.services.AuthorService;
import com.springbootlab0.approach_1.services.DemoService;
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

    private static final String RESPONSE_MESSAGE = "responseMessage";
    private static final String AUTHORS_ATTR = "authors";
    private static final String PUBLICATION_TO_UPDATE = "publicationToUpdate";
    private static final String UPDATED_TEXT = " updated!";
    private static final String UPDATE_PUBLICATION_HTML = "publications/updatePublication";
    private static final String NOT_FOUND_HTML = "notFound";


    @Autowired
    PublicationService publicationService;

    @Autowired
    AuthorService authorService;

    @Autowired
    DemoService demoService;

    @GetMapping(value = {"/", ""})
    public String index(Model containerToView) {
        // Compose the container with all the information that the publicationsIndex.html
        // requires on render time
        // 1. Retrieve all publications
        containerToView.addAttribute("publicationsFromController",
                publicationService.getAllPublications());
        return "/publications/publicationsIndex";
    }

    // TODO: Unify all methods that access the same forms (i.e: Publication, Author, etc...)
    @GetMapping("/publicationForm")
    public String publicationForm(ModelMap containerToView) {
        containerToView.addAttribute("newBook", new Book());
        containerToView.addAttribute(AUTHORS_ATTR, authorService.getAllAuthors());
        containerToView.addAttribute(RESPONSE_MESSAGE, null);
        return "publications/publicationForm";
    }

    @GetMapping("/createPublication")
    public String createPublication(@ModelAttribute("newBook") Book newBook, ModelMap containerToView, BindingResult result) {
        // TODO: Logic to create any Publication, not just a Book
        // TODO: Check if the publication already exists before inserting it again
        if (result.hasErrors()) {
            containerToView.addAttribute(RESPONSE_MESSAGE, "Invalid book object! Review the fields");
        }
        else {
            publicationService.createPublication(newBook);
            containerToView.addAttribute("newBook", new Book());
            containerToView.addAttribute(AUTHORS_ATTR, authorService.getAllAuthors());
            containerToView.addAttribute(RESPONSE_MESSAGE, "Book " + newBook.getTitle() + " from " + newBook.getAuthor().getFirstName() + " " + newBook.getAuthor().getLastName() + " saved.");
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
            containerToView.addAttribute(PUBLICATION_TO_UPDATE, publicationToUpdate.get());
            containerToView.addAttribute(AUTHORS_ATTR, authorService.getAllAuthors());
            containerToView.addAttribute(RESPONSE_MESSAGE, null);
            return UPDATE_PUBLICATION_HTML;
        }
        else return NOT_FOUND_HTML;
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
        Optional<Publication> publicationToUpdate =  publicationService.findPublicationById(id);
        // Check if the Book is already inserted
        if (publicationToUpdate.isPresent() && updatedPublication.isPresent()) {
            publicationService.createPublication(updatedPublication.get());
            containerToView.addAttribute(PUBLICATION_TO_UPDATE, updatedPublication.get());
            containerToView.addAttribute(AUTHORS_ATTR, authorService.getAllAuthors());
            containerToView.addAttribute(RESPONSE_MESSAGE, "Book " + publicationToUpdate.get().getTitle() + UPDATED_TEXT);
            return UPDATE_PUBLICATION_HTML;
        }
        return NOT_FOUND_HTML;
    }

    @PostMapping("/updatePublication/CD/{id}")
    public String updateCD(@PathVariable("id") String id, Optional<CD> updatedPublication, ModelMap containerToView) {
        // Retrieve the publication based on the received ID
        Optional<Publication> publicationToUpdate =  publicationService.findPublicationById(id);
        // Check if the CD is already inserted
        if (publicationToUpdate.isPresent() && updatedPublication.isPresent()) {
            publicationService.createPublication(updatedPublication.get());
            containerToView.addAttribute(PUBLICATION_TO_UPDATE, publicationToUpdate.get());
            containerToView.addAttribute(AUTHORS_ATTR, authorService.getAllAuthors());
            containerToView.addAttribute(RESPONSE_MESSAGE, "CD " + publicationToUpdate.get().getTitle() + UPDATED_TEXT);
            return UPDATE_PUBLICATION_HTML;
        }
        return NOT_FOUND_HTML;
    }

    @PostMapping("/updatePublication/DVD/{id}")
    public String updateDVD(@PathVariable("id") String id, Optional<DVD> updatedPublication, ModelMap containerToView) {
        // Retrieve the publication based on the received ID
        Optional<Publication> publicationToUpdate =  publicationService.findPublicationById(id);
        // Check if the DVD is already inserted
        if (publicationToUpdate.isPresent() && updatedPublication.isPresent()) {
            publicationService.createPublication(updatedPublication.get());
            containerToView.addAttribute(PUBLICATION_TO_UPDATE, publicationToUpdate.get());
            containerToView.addAttribute(AUTHORS_ATTR, authorService.getAllAuthors());
            containerToView.addAttribute(RESPONSE_MESSAGE, "DVD " + publicationToUpdate.get().getTitle() + UPDATED_TEXT);
            return UPDATE_PUBLICATION_HTML;
        }
        return NOT_FOUND_HTML;
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
            redirectAttrs.addFlashAttribute(RESPONSE_MESSAGE, "Publication " + publicationToDelete.get().getTitle() + " deleted");
            return "redirect:/publications";
        }
        else return NOT_FOUND_HTML;
    }

    @GetMapping("/authorForm")
    public String authorForm(ModelMap containerToView) {
        containerToView.addAttribute("newAuthor", new Author());
        containerToView.addAttribute(RESPONSE_MESSAGE, null);
        return "publications/authorForm";
    }

    @GetMapping("/createAuthor")
    public String createAuthor(@ModelAttribute("newAuthor") Author newAuthor, ModelMap containerToView, BindingResult result) {
        if (result.hasErrors()) {
            containerToView.addAttribute(RESPONSE_MESSAGE, "Invalid author object! Review the fields");
        }
        else {
            authorService.createAuthor(newAuthor);
            containerToView.addAttribute("newAuthor", new Author());
            containerToView.addAttribute(RESPONSE_MESSAGE, "Author " + newAuthor.getFirstName() + " " + newAuthor.getLastName() + " saved!");
        }
        return "publications/authorForm";
    }


    /**
     * Demo purposes: Create the number of expected publications
     * and insert them into the DB
     */
    @GetMapping(value = "/createFakePublications")
    public String createFakePublications(@RequestParam("qtyPublications") int qty, RedirectAttributes redirectAttributes) {
        // Call the faker generator
        demoService.createFakePublications(qty);
        redirectAttributes.addFlashAttribute(RESPONSE_MESSAGE, "Added 10 new Publications");
        // Redirect to the Publications page
        return "redirect:/publications";
    }
}
