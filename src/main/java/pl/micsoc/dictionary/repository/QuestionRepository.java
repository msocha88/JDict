package pl.micsoc.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.micsoc.dictionary.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
