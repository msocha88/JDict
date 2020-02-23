package pl.micsoc.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.micsoc.dictionary.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
