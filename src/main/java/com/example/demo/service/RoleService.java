package com.example.demo.service;

import com.example.demo.entity.Role;
import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
    Role getRole(Long id);
    Role findByName(String name);
    Role updateRole(Long id, Role role);
    void deleteRole(Long id);
}