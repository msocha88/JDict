package pl.micsoc.dictionary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/hello")
    public String sayElo() {
        return "hello";
    }

    @GetMapping("/golebaby")

    public String aleDupa() {
        return "Gola baba!!";
    }
}

