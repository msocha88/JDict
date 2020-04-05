package pl.micsoc.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.micsoc.dictionary.exceptions.WrongUserException;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.service.EntryService;
import pl.micsoc.dictionary.service.QuestionService;
import pl.micsoc.dictionary.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/panel/")
    public String userPanel(@PathVariable String name, ModelMap modelMap) throws WrongUserException{
        if (userService.checkUser(name)) {
            modelMap.put("user", userService.findUserByUserName(name));
            modelMap.put("alternativeUser", new User());
        } else {
            throw new WrongUserException();
        }

        return "user/userPanel";
    }

    @GetMapping("/entries/{name}")
    public String userEntries(@PathVariable String name, ModelMap modelMap) throws WrongUserException {


        if (userService.checkUser(name)) {
        modelMap.put("entries", entryService.findEntriesFromUser(userService.findUserByUserName(name)));
        } else {
            throw new WrongUserException();
        }

        return "user/myEntries";
    }

    @GetMapping("/myquestions")
    public String usersQuestions(ModelMap modelMap) {

        modelMap.put("questions", questionService.findQuesionsOfUser(userService.currentUser().getUserName()));

        return "user/myQuestions";
    }

}
