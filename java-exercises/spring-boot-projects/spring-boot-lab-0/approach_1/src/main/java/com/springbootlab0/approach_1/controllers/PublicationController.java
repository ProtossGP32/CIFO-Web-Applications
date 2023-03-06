package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.domain.Book;
import com.springbootlab0.approach_1.services.AuthorService;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/form")
    public String publicationForm(ModelMap containerToView) {
        containerToView.addAttribute("newBook", new Book());
        containerToView.addAttribute("authors", authorService.getAllAuthors());
        containerToView.addAttribute("responseMessage", null);
        return "/publications/publicationForm";
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
}
