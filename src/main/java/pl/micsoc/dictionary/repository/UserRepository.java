package pl.micsoc.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.micsoc.dictionary.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUserName(String userName);
}
