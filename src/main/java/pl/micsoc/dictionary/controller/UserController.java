package pl.micsoc.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.micsoc.dictionary.model.PasswordChanger;
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

    @GetMapping("/edituser")
    public String editUser(ModelMap modelMap) {
        modelMap.put("user", userService.currentUser());
        modelMap.put("alternate", new User());
        modelMap.put("passChanger", new PasswordChanger());

        return "user/editUser";
    }

    @PostMapping("/edituser")
    public String userEdited(@ModelAttribute(name = "alternate") User user, ModelMap modelMap) {

        userService.updateCurrentUserData(user);
        modelMap.put("successMessage", "Dane zostały zmienione");

        return "redirect:/user/edituser";
    }

    @PostMapping("/changepassword")
    public String changePassword(@ModelAttribute(name = "passChanger") PasswordChanger passwordChanger,
                                 ModelMap modelMap) {

        if (!userService.checkPassword(passwordChanger.getOldPassword())) {
            modelMap.put("wrongPassword", "Podane hasło nie jest poprawne");
        } else if (!passwordChanger.getNewPassword().equals(passwordChanger.getConfirmPassword())) {
            modelMap.put("wrongConfirm", "Podane nowe hasła różnią się od siebie");
        } else {
            User user = new User();
            user.setPassword(passwordChanger.getNewPassword());
            userService.updateCurrentUserData(user);
            modelMap.put("successMmessage", "Hasło zostało zmienione");
        }

        return "redirect:/user/edituser";
    }
}
