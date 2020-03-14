package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.micsoc.dictionary.model.Role;
import pl.micsoc.dictionary.repository.RoleRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    public Set<Role> findById(int id) {
        Role role = roleRepository.findById(id).get();

        return new HashSet<Role>(Collections.singleton(role));
    }
}
