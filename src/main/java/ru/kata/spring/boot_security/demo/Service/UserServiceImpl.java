package ru.kata.spring.boot_security.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Models.User;
import ru.kata.spring.boot_security.demo.Models.Role;
import ru.kata.spring.boot_security.demo.Repositories.RoleRepositoryImpl;
import ru.kata.spring.boot_security.demo.Repositories.UserRepositoryImpl;
import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    private final RoleRepositoryImpl roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepository, RoleRepositoryImpl roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public User findUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("User with ID %d not found", id)));

    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(User user, Integer id) {
        User userToBeUpdated = userRepository.findById(id).orElse(null);
        if (userToBeUpdated == null){
            throw new NullPointerException("User для редактирования не найден");
        }
        userToBeUpdated.setUsername(user.getUsername());

        userToBeUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeUpdated.setAge(user.getAge());

        userToBeUpdated.setRoles(user.getRoles());
        userRepository.save(userToBeUpdated);
    }
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
