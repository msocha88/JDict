package pl.micsoc.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {


    @GetMapping("/")
    public String sayElo() {
        return "redirect:/entry/show";
    }


}

