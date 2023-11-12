package com.example.peterspetitions;


import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.*;

@Controller
public class PetitionController {


    private void coreActions(Model model, String title){
        model.addAttribute("petitions", Petition.getAllPetitions());
        model.addAttribute("title", title);
    }

    @GetMapping(value = "/")
    public String home(Model model){
        this.coreActions(model, "Home");
        return "index";
    }


    @GetMapping(value = "/view/{name}")
    public String petition(Model model, @PathVariable(required = false) String name){

        List<Petition> somePetitions = Petition.getAllPetitions().stream().filter(pt -> pt.getUniqueTitle().equalsIgnoreCase(name)).toList();
        if (somePetitions.isEmpty())
            return "404";
        Petition thePetition = somePetitions.get(0);
        model.addAttribute("petition", thePetition);
        model.addAttribute("title", thePetition.getTitle());
        return "view-petition";

    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/view")
    public String view(Model model){
        this.coreActions(model, "View All Petitions");
        return "view";
    }

    @GetMapping(value = "/create")
    public String create(Model model){
        this.coreActions(model, "Create Petition");

        return "index";
    }

    @GetMapping(value = "/search")
    public String search(Model model){
        this.coreActions(model, "Search Petitions");
        return "index";
    }







}
