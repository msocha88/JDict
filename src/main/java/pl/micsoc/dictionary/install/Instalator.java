package pl.micsoc.dictionary.install;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.micsoc.dictionary.model.Category;
import pl.micsoc.dictionary.model.Role;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.repository.UserRepository;
import pl.micsoc.dictionary.service.CategoryService;
import pl.micsoc.dictionary.service.RoleService;
import pl.micsoc.dictionary.service.UserService;


@Component
public class Instalator implements CommandLineRunner {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {


        roleService.save(new Role("ROLE_USER"));
        roleService.save(new Role("ROLE_MODERATOR"));
        roleService.save(new Role("ROLE_ADMIN"));

        categoryService.save(new Category("Definicja"));
        categoryService.save(new Category("Pytanie"));

        User user1 = new User();
        user1.setName("Michał");
        user1.setLastName("Socha");
        user1.setRoles(roleService.allRoles());
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
