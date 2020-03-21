package pl.micsoc.dictionary.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.micsoc.dictionary.model.Answer;
import pl.micsoc.dictionary.model.Question;
import pl.micsoc.dictionary.service.AnswerService;
import pl.micsoc.dictionary.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QAController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;


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
        return "redirect:/questions/";
    }

    @GetMapping("/{id}")
    public String showQuestion(@PathVariable String id, ModelMap modelMap) {

        modelMap.put("question", questionService.findById(id));
        modelMap.put("answers", questionService.allAnswersOfQuestion(id));
       Answer answer = new Answer();
        modelMap.put("newAnswer", answer);

        return "question";
    }

    @PostMapping("/{id}")
    public String addAnswer(@PathVariable String id, @ModelAttribute(name = "newAnswer") Answer answer) {

        answerService.addAnswer(id, answer);

        return "redirect:/questions/{id}";
    }

    @GetMapping("/{questionId}/deleteanswer/{answerId}")
    public String deleteAnswer(@PathVariable("questionId") String questionId,
                               @PathVariable("answerId") String answerId) {

        answerService.deleteAnswer(answerId);

        return "redirect:/questions/{questionId}";
    }

    @GetMapping("/{questionId}/editanswe/{answerId}")
    public String editAnswer(@PathVariable("questionId") String questionId,
                               @PathVariable("answerId") String answerId
    ,ModelMap modelMap) {

        modelMap.put("answer",answerService.findAnswer(answerId));

        return "editanswer";
    }


    @GetMapping("/company/{companyName}")
    public String allQuesionsOfCompany(@PathVariable String companyName, ModelMap modelMap) {

        modelMap.put("questions",questionService.allQuestionsofCompany(companyName));

        return "QuestionOfCompany";
    }


}
