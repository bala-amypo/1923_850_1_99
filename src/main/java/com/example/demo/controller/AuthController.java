package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        User user = new User();
        user.setEmail(request.get("email"));
        user.setName(request.get("name"));
        user.setPassword(passwordEncoder.encode(request.get("password")));
        
        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));
        user.getRoles().add(userRole);
        
        User saved = userRepository.save(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", saved.getId());
        response.put("email", saved.getEmail());
        response.put("name", saved.getName());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        Optional<User> userOpt = userRepository.findByEmail(request.get("email"));
        if (userOpt.isEmpty() || !passwordEncoder.matches(request.get("password"), userOpt.get().getPassword())) {
            return ResponseEntity.status(401).build();
        }
        
        User user = userOpt.get();
        Set<String> roles = new HashSet<>();
        user.getRoles().forEach(r -> roles.add(r.getName()));
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user.getEmail());
        
        return ResponseEntity.ok(response);
    }
}