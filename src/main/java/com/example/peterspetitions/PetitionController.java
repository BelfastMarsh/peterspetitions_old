package com.example.peterspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetitionController {
    public static String[] petitions = new String[]{"petition1","petition2"};
    @RequestMapping(value = "")
    public String index(Model model){
        return "index";
    }


}
