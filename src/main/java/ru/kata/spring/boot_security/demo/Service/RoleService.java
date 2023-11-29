package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.Models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void save(Role role);

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Integer roleId);

}
