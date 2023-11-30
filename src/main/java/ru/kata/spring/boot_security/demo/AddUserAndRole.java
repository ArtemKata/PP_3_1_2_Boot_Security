package ru.kata.spring.boot_security.demo;

import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddUserAndRole implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;


    public AddUserAndRole(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        roleService.save(userRole);
        roleService.save(adminRole);

        List<Role> roles = new ArrayList<>();
        List<Role> rolesAdm = new ArrayList<>();

        roles.add(userRole);

        rolesAdm.add(adminRole);

        User user = new User("user", 20, "111", roles);
        User admin = new User("admin", 20, "222", rolesAdm);


        userService.save(user);
        userService.save(admin);
    }
}
