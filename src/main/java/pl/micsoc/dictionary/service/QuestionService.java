package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.micsoc.dictionary.model.Question;
import pl.micsoc.dictionary.repository.QuestionRepository;
import pl.micsoc.dictionary.repository.UserRepository;

import java.sql.Date;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(Question question) {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            question.setAuthor(userRepository.findByUserName(((UserDetails) principal).getUsername()));
        } else {
            question.setAuthor(userRepository.findByUserName(principal.toString()));
        }

        question.setDate((new Date(System.currentTimeMillis())));

        questionRepository.save(question);

    }

    public List<Question> allQuestions() {

        return questionRepository.findAll();
    }
}
