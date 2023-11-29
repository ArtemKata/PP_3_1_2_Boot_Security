package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Models.Role;
import ru.kata.spring.boot_security.demo.Models.User;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.allUsers());

        return "showUsers";
    }
    @GetMapping("/showByID")
    public String show(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("user", userService.findUser(id));
        return "adminInfoUser";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user, @ModelAttribute("roles") List<Integer> rolesId) {
        List<Role> roles = new ArrayList<>();
        for (int roleId : rolesId) {
            roles.add(roleService.getRoleById(roleId).get());
        }
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.findUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("id") int id,
                         @ModelAttribute("roles") List<Integer> rolesId) {
        if (!rolesId.isEmpty()) {
            List<Role> roles = new ArrayList<>();
            for (int roleId : rolesId) {
                roles.add(roleService.getRoleById(roleId).get());
            }
            user.setRoles(roles);
        }
        userService.update(user, id);
        return "redirect:/admin";
    }
    @DeleteMapping("{id}/delete")
    public String delete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/admin";
    }
}




