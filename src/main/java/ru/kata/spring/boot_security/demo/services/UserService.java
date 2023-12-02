package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService  {
    User getUserById(long id);
    void save(User user);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void update(long id, User user);

    void removeUser(long id);

}
