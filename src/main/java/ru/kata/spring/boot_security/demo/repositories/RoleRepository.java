package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.nio.file.LinkOption;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(String name);


}
