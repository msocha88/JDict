package pl.micsoc.dictionary.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.micsoc.dictionary.model.Role;
import pl.micsoc.dictionary.model.UserForm;

import java.util.*;

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

