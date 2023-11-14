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
    @GetMapping(value = "/{page}/")
    public String redirectView(Model model, @PathVariable String page){
        return "redirect:/" + page;
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
        return "search";
    }

    @PostMapping(value = "/search/result")
    public String searchResult(@RequestParam("search-text") String searchValue,
                               Model model){
        System.out.println(searchValue);
        String[] bits = searchValue.split(" ");
        HashMap<String, WeightedPetition> weightedPetitions = new HashMap<>();

        // this is the mad weighting algorithm I came up with
        /*
         add 1 to weighting if title contains word as part word
         add 3 to weighting if title contains word as full word
         add 10 to weighting if title == complete search term
         add 5 to weighting if title contains complete search term.
        */
        for (String bit : bits){
            List<Petition> matchingPetitions =
                Petition.getAllPetitions().stream().filter(p ->
                        p.getTitle().toLowerCase().contains(bit.toLowerCase())).toList();
            for (Petition petition : matchingPetitions){
                if (!weightedPetitions.containsKey(petition.getUniqueTitle())){
                    weightedPetitions.put(petition.getUniqueTitle(), new WeightedPetition(0,petition));
                }
                if (weightedPetitions.get(petition.getUniqueTitle()).getPetition().getTitle(" ").
                        toLowerCase().contains(" " + bit.toLowerCase() + " ")){
                    weightedPetitions.get(petition.getUniqueTitle()).addWeighting(3);
                }
                else {
                    weightedPetitions.get(petition.getUniqueTitle()).addWeighting(1);
                }
            }
        }
        for (WeightedPetition weightedPetition : weightedPetitions.values()) {
            if (weightedPetition.getPetition().getTitle().equalsIgnoreCase(searchValue))
                weightedPetition.addWeighting(10);
            else if (weightedPetition.getPetition().getTitle().toLowerCase().
                    contains(searchValue.toLowerCase()))
                weightedPetition.addWeighting(5);
        }

        //sort weighted petitions in reverse order of weighting
        List<WeightedPetition> weightedPetitionsArray =
                weightedPetitions.values().stream().
                        sorted(Comparator.comparingInt(WeightedPetition::getWeighting).reversed()).toList();
        for(WeightedPetition wp : weightedPetitionsArray)
            System.out.println(wp.getPetition().getUniqueTitle() + ": " + wp.getWeighting());

        model.addAttribute("petitions", weightedPetitionsArray);
        return "search-result";
    }



}
