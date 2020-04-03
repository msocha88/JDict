package pl.micsoc.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.micsoc.dictionary.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
