package ru.kata.spring.boot_security.demo.Service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Models.Role;
import ru.kata.spring.boot_security.demo.Models.User;

import java.util.List;
@Service
public interface UserService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User findUser(Integer id);
    List<User> allUsers();
    void save(User user);
    void update(User user, Integer id);

    void delete(Integer id);

    List<Role> findAllRoles();


}
