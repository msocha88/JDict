package pl.micsoc.dictionary.install;

import javassist.bytecode.stackmap.TypeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.micsoc.dictionary.model.Role;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.repository.RoleRepository;
import pl.micsoc.dictionary.repository.UserRepository;
import pl.micsoc.dictionary.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class Instalator implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        Set<Role> roleSet = new HashSet<>(roleRepository.findAll());

        Role user = new Role("ROLE_USER");
        if (!roleSet.contains(roleRepository.findByRole(user.getRole()))) {
            roleRepository.save(user);
        }
        Role mod = new Role("ROLE_MODERATOR");
        if (!roleSet.contains(roleRepository.findByRole(mod.getRole()))) {
            roleRepository.save(mod);
        }
        Role admin = new Role("ROLE_ADMIN");
        if (!roleSet.contains(roleRepository.findByRole(admin.getRole()))) {
            roleRepository.save(admin);
        }


        User user1 = new User();
        user1.setName("Michał");
        user1.setLastName("Socha");
        user1.setRoles(roleSet);
        user1.setPassword("kaskada");
        user1.setUserName("admin");
        user1.setActive(true);
        user1.setEmail("socher@wp.pl");

        if (!userRepository.findAll().stream().anyMatch(x -> x.getEmail().equals(user1.getEmail()))
        ) {
            userService.saveUser(user1);
        }
    }
}
