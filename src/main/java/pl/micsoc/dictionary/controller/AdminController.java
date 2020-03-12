package pl.micsoc.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.micsoc.dictionary.service.RoleService;
import pl.micsoc.dictionary.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping()
    public String adminPanel() {
        return "adminPanel";
    }

    @GetMapping("/users")
    public String adminUserList(ModelMap modelMap) {

        modelMap.put("roles", roleService.listAll());
        modelMap.put("users", userService.findAll());

        return "adminUserList";
    }
}
