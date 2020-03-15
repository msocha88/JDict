package pl.micsoc.dictionary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private Date date;

    @ManyToOne
    private User author;

    private int points;

    @ManyToOne
    private Question question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return id == answer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
