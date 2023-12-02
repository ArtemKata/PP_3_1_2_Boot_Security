package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("User with ID %d not found", id)));
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void update(long id, User user) {
        User userToBeUpdated = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("User для редактирования не найден"));

        userToBeUpdated.setUsername(user.getUsername());
        userToBeUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeUpdated.setAge(user.getAge());
        userToBeUpdated.setRoles(user.getRoles());

        userRepository.save(userToBeUpdated);

    }

    @Override
    public void removeUser(long id) {
        userRepository.deleteById(id);
    }
}
