package pl.micsoc.dictionary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Entry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Date date;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User userEntry;

    @Transient
    private String selectedCategory;

}
