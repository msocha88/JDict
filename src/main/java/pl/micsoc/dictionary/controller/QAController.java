package pl.micsoc.dictionary.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.micsoc.dictionary.model.Question;
import pl.micsoc.dictionary.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QAController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String allQuestions(ModelMap modelMap) {

        modelMap.put("questions", questionService.allQuestions());

        return "questions";
    }

    @GetMapping("/add")
    public String addQuestion(ModelMap modelMap) {

        modelMap.put("question", new Question());
        return "addQuestion";
    }

    @PostMapping("/add")
    public String addedQuestion(@ModelAttribute("question") Question question) {

        questionService.save(question);
        return "redirect:questions/";
    }
}
