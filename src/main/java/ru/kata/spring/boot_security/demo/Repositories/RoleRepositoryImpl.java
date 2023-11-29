package ru.kata.spring.boot_security.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.Models.Role;
@Repository
public interface RoleRepositoryImpl extends JpaRepository<Role, Integer> {


}
