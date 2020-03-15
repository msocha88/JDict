package pl.micsoc.dictionary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String company;

    private String title;

    private String content;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    private Date date;




}
