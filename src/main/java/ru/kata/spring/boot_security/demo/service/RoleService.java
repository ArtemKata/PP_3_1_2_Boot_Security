package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void save(Role role);

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long roleId);

}
