package pl.micsoc.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.service.EntryService;
import pl.micsoc.dictionary.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EntryService entryService;

    @GetMapping("/{name}")
    public String userDesktop(@PathVariable String name, ModelMap modelMap){

        User user = userService.findUserByUserName(name);

        modelMap.put("entries", entryService.findEntriesFromUser(user));

        return "myEntries";
    }
}
