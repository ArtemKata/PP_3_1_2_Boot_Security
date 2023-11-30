package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserService {

    User findUser(Long id);

    List<User> allUsers();

    void save(User user);

    void update(User user, Long id);

    void delete(Long id);

    User getUserByUsername(String username);


}
