package com.example.peterspetitions;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Map;

@Controller
public class PetitionController {


    private void coreActions(Model model){
        model.addAttribute("petitions", Petition.getPetitions());
    }

    @GetMapping(value = "/")
    public String home(Model model){
        this.coreActions(model);
        model.addAttribute("title", "View All Petitions");
        return "index";
    }

    @GetMapping(value = "/view")
    public String view(Model model){
        this.coreActions(model);
        model.addAttribute("title", "View All Petitions");
        return "index";
    }

    @GetMapping(value = "/create")
    public String create(Model model){
        this.coreActions(model);
        model.addAttribute("title", "Create Petition");
        return "index";
    }

    @GetMapping(value = "/ssearch")
    public String search(Model model){
        this.coreActions(model);
        model.addAttribute("title", "Search Petitions");
        return "index";
    }







}
