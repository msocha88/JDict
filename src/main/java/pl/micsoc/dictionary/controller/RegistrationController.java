package pl.micsoc.dictionary.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.micsoc.dictionary.model.Role;
import pl.micsoc.dictionary.model.UserForm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RegistrationController {


    @GetMapping("/registration")
    public String register(ModelMap modelMap) {
        modelMap.put("newUser", new UserForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam UserForm userForm) {

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ADMIN"));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        UserDetails user = new User(userForm.getUsername(),userForm.getPassword(),grantedAuthorities);

        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager(user);
        return "registered";
    }
}
