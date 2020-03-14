package pl.micsoc.dictionary.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.micsoc.dictionary.model.Role;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.service.RoleService;
import pl.micsoc.dictionary.service.UserService;

import java.util.HashSet;
import java.util.Set;

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

        modelMap.put("users", userService.findAll());
        modelMap.put("roles", roleService.listAll());
        modelMap.put("newRole", new Role());

        return "adminUserList";
    }


    @RequestMapping(value = "/changeuserrole/{name}", method =RequestMethod.POST)
    public String changeUserRole(@PathVariable String name,@NotNull @ModelAttribute("newRole") Role role) {

        User user= userService.findUserByUserName(name);
        Set<Role> roles = new HashSet<>(roleService.findById(role.getId()));

        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable String id) {

        userService.deleteUser(id);

        return "redirect:/admin/users";
    }
}
