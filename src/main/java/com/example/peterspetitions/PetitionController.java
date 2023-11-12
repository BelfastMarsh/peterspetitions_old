package com.example.peterspetitions;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


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


    @GetMapping(value = "/view/{pttn}")
    public String petition(Model model, @PathVariable String pttn){

        List<Petition> somePetitions = Petition.getAllPetitions().stream().filter(pt -> pt.getUniqueTitle().equalsIgnoreCase(pttn)).toList();
        if (somePetitions.isEmpty())
            return "404";
        model.addAttribute("uTitle", pttn);
        Petition thePetition = somePetitions.get(0);
        model.addAttribute("petition", thePetition);
        model.addAttribute("title", thePetition.getTitle());
        return "view-petition";

    }


    @PostMapping(value = "/view/{pttn}/add")
    public String addSignature(@RequestParam("name") String name, @RequestParam("email") String email, @PathVariable String pttn){
        List<Petition> somePetitions = Petition.getAllPetitions().stream().filter(pt -> pt.getUniqueTitle().equalsIgnoreCase(pttn)).toList();

        if (somePetitions.isEmpty()) return "404";

        Petition p = somePetitions.get(0);
        p.addSignatory(name, email);
        return "redirect:/view/"+pttn;
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
        model.addAttribute("title", "Create Petition");
        return "create";
    }

    @PostMapping(value = "/create/new")
    public String createPetition(@RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("authorName") String name,
                                 @RequestParam("authorEmail") String email,
                                 Model model) {
        model.addAttribute("title", "Create Petition");
        User author = new User(name, email);
        Petition petition = new Petition(title, description, author);

        return "redirect:/view/"+petition.getUniqueTitle();
    }

    @GetMapping(value = "/search")
    public String search(Model model){
        this.coreActions(model, "Search Petitions");
        return "index";
    }



}
