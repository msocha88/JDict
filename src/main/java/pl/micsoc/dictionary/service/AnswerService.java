package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.micsoc.dictionary.model.Answer;
import pl.micsoc.dictionary.repository.AnswerRepository;
import pl.micsoc.dictionary.repository.UserRepository;

import java.sql.Date;

@Service
public class AnswerService {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    public void addAnswer(String id, Answer answer) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            answer.setAuthor(userRepository.findByUserName(((UserDetails) principal).getUsername()));
        } else {
            answer.setAuthor(userRepository.findByUserName(principal.toString()));
        }

        answer.setQuestion(questionService.findById(id));
        answer.setDate(new Date(System.currentTimeMillis()));
        answer.setId(null);
        questionService.addAnswer(id, answer);

        answerRepository.save(answer);
    }

    public void deleteAnswer(String answerId) {
        answerRepository.delete(answerRepository.findById(Long.parseLong(answerId)).get());
    }

    public Answer findAnswer(String answerId) {
        return answerRepository.findById(Long.parseLong(answerId)).get();
    }
}
