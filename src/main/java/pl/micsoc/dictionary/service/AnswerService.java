package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.micsoc.dictionary.model.Answer;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.repository.AnswerRepository;
import pl.micsoc.dictionary.repository.UserRepository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    private UserService userService;

    public void addAnswer(String id, Answer answer) {

        answer.setAuthor(userService.currentUser());
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

    public void updateAnswer(String answerId, Answer answer) {
        Answer newAnswer = answerRepository.findById(Long.parseLong(answerId)).get();
        newAnswer.setContent(answer.getContent());
        answerRepository.save(newAnswer);
    }

    public List<Answer> allAnswersOfUser(User user) {
        return answerRepository.findAll()
                .stream()
                .filter(s -> s.getAuthor().equals(user))
                .collect(Collectors.toList());
    }
}
