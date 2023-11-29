package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Models.User;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public String showUserInfo(@CurrentSecurityContext(expression = "authentication.principal") User principal, Model model) {
        model.addAttribute("user", principal);
        return "user";
    }
}
