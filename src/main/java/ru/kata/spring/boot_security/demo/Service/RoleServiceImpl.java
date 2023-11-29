package ru.kata.spring.boot_security.demo.Service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Models.Role;
import ru.kata.spring.boot_security.demo.Repositories.RoleRepositoryImpl;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{


    private final RoleRepositoryImpl roleRepository;

    public RoleServiceImpl(RoleRepositoryImpl roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Integer roleId) {
        return roleRepository.findById(roleId);
    }
}
