package pl.micsoc.dictionary.model;



import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    @Length(min = 3, message = "*Nazwa musi składać się z conajmniej 3 znaków")
    @NotEmpty(message = "*Proszę podać nazwę użytkownika")
    private String userName;

    @Column(name = "email")
    @Email(message = "*Proszę podać poprawny adres email")
//    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Hasło musi skłądać się z conajmniej 5 znaków")
    @NotEmpty(message = "*Proszę wpisać hasło")
    private String password;

    @Column(name = "name")
//    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "userEntry")
    private Set<Entry> entries;

    @OneToMany(mappedBy = "author")
    private Set<Question> questions;

    @OneToMany
    @JoinTable(name = "user_answer", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private Set<Answer> answers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }
}
