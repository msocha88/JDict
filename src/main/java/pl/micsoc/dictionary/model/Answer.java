package pl.micsoc.dictionary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

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

}
