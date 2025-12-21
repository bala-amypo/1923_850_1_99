package com.example.demo.config;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role("ADMIN");
            Role userRole = new Role("USER");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
            
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setName("Admin User");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setCreatedAt(LocalDateTime.now());
                admin.setRoles(Set.of(adminRole, userRole));
                userRepository.save(admin);
                
                User normalUser = new User();
                normalUser.setName("Normal User");
                normalUser.setEmail("user@example.com");
                normalUser.setPassword(passwordEncoder.encode("user123"));
                normalUser.setCreatedAt(LocalDateTime.now());
                normalUser.setRoles(Set.of(userRole));
                userRepository.save(normalUser);
            }
        }
    }
}