package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.micsoc.dictionary.model.Answer;
import pl.micsoc.dictionary.model.Question;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.repository.QuestionRepository;
import pl.micsoc.dictionary.repository.UserRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void save(Question question) {

        question.setAuthor(userService.currentUser());

        question.setDate((new Date(System.currentTimeMillis())));

        questionRepository.save(question);

    }

    public List<Question> allQuestions() {

        return questionRepository.findAll();
    }

    public Question findById(String id) {

        return questionRepository.findById(Integer.valueOf(id)).get();
    }

    public List<Answer> allAnswersOfQuestion(String id) {
        Question question = questionRepository.findById(Integer.valueOf(id)).get();
        return new ArrayList<>(question.getAnswers());
    }

    public void addAnswer(String id, Answer answer) {
        allAnswersOfQuestion(id).add(answer);
        questionRepository.save(questionRepository.findById(Integer.valueOf(id)).get());
    }

    public List<Question> allQuestionsofCompany(String companyName) {
        return questionRepository.findAll()
                .stream()
                .filter(s -> s.getCompany().equals(companyName))
                .collect(Collectors.toList());
    }

    public void deleteQuestion(String id) {
        questionRepository.delete(questionRepository.findById(Integer.valueOf(id)).get());
    }

    public void updateQuestion(String id, Question question) {
        Question toUpdate = questionRepository.findById(Integer.valueOf(id)).get();

        toUpdate.setCompany(question.getCompany());
        toUpdate.setTitle(question.getTitle());
        toUpdate.setContent(question.getContent());

        questionRepository.save(toUpdate);
    }

    public List<Question> findQuesionsOfUser(User user) {
        return questionRepository.findAll().stream()
                .filter(s -> s.getAuthor().equals(user))
                .collect(Collectors.toList());
    }
}
