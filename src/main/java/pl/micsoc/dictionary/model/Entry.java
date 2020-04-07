package pl.micsoc.dictionary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Entry {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private Date date;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User userEntry;

    @Transient
    private String selectedCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Objects.equals(id, entry.id) &&
                Objects.equals(title, entry.title) &&
                Objects.equals(content, entry.content) &&
                Objects.equals(date, entry.date) &&
                Objects.equals(category, entry.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, date, category);
    }


}
