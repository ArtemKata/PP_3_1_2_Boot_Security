package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role save(Role userRole);

    Role getRoleByName(String name);

    List<Role> findAllBy();

    Role getRoleById(int roleId);
}
