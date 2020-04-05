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
import pl.micsoc.dictionary.service.AnswerService;
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

    @Autowired
    private AnswerService answerService;

    @GetMapping("/panel")
    public String userPanel() {

        return "user/userPanel";
    }

    @GetMapping("/entries")
    public String userEntries(ModelMap modelMap) {

        modelMap.put("entries", entryService.findEntriesFromUser(userService.currentUser()));

        return "user/myEntries";
    }

    @GetMapping("/myquestions")
    public String usersQuestions(ModelMap modelMap) {

        modelMap.put("questions", questionService.findQuesionsOfUser(userService.currentUser()));

        return "user/myQuestions";
    }

    @GetMapping("/myanswers")
    public String usersAnswers(ModelMap modelMap) {
        modelMap.put("answers", answerService.allAnswersOfUser(userService.currentUser()));

        return "user/myAnswers";
    }
}
