package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.micsoc.dictionary.exceptions.WrongUserException;
import pl.micsoc.dictionary.model.Role;
import pl.micsoc.dictionary.model.User;
import pl.micsoc.dictionary.repository.RoleRepository;
import pl.micsoc.dictionary.repository.UserRepository;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    public void deleteUser(String id) {

        userRepository.delete(userRepository.findById(Integer.valueOf(id)).get());

    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public boolean checkUser(String name) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails && ((UserDetails) principal).getUsername().equals(name)) {
            return true;
        } else {
            return false;
        }
    }

    public User currentUser(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return findUserByUserName(((UserDetails) principal).getUsername());
        }
}