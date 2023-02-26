package com.springbootlab0.approach_1.controllers;

import com.springbootlab0.approach_1.services.DemoService;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibraryWebController {

    // Add some autowired references
    @Autowired
    PublicationService publicationService;
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
        containerToView.addAttribute("publicationsFromController",
                publicationService.getAllPublications());
        return "showPublications";
    }

    @RequestMapping(value = "/clearPublications")
    public String clearPublicationsFromH2() {
        // Clear all the H2 Publications DB
        publicationService.deleteAll();
        // Redirect to the Publications page
        return "redirect:publications";
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
}
