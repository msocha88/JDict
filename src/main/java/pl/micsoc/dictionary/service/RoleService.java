package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.micsoc.dictionary.model.Role;
import pl.micsoc.dictionary.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public void save(Role role) {

        if (!roleRepository.findAll().contains(roleRepository.findByRole(role.getRole()))) {
            roleRepository.save(role);
        }

    }

    public Set<Role> allRoles() {
        return new HashSet<>(roleRepository.findAll());
    }
}
