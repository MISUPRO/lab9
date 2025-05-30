package org.example.simplewebappspring.security;

import org.example.simplewebappspring.model.UserEntity;
import org.example.simplewebappspring.model.AdminEntity;
import org.example.simplewebappspring.repository.UserRepository;
import org.example.simplewebappspring.repository.AdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;
    private final AdminRepository adminRepo;

    public CustomUserDetailsService(UserRepository userRepo, AdminRepository adminRepo) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Сначала ищем админа
        Optional<AdminEntity> adminOpt = adminRepo.findByName(username);
        if (adminOpt.isPresent()) {
            AdminEntity admin = adminOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    admin.getName(),
                    admin.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + admin.getRole().toUpperCase())) // будет "ROLE_ADMIN"
            );
        }

        // Потом обычного пользователя
        Optional<UserEntity> userOpt = userRepo.findByName(username);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getName(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())) // будет "ROLE_USER"
            );
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminEntity admin = adminRepo.findByName(username).orElse(null);
        if (admin != null) {
            return new User(admin.getName(), admin.getPassword(),
                    Collections.singletonList(() -> "admin"));
        }

        UserEntity user = userRepo.findByName(username).orElse(null);
        if (user != null) {
            return new User(user.getName(), user.getPassword(),
                    Collections.singletonList(() -> "user"));
        }

        throw new UsernameNotFoundException("User not found: " + username);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (adminRepo.findByName(username).isPresent()) {
            AdminEntity admin = adminRepo.findByName(username).get();
            return new User(admin.getName(), admin.getPassword(), List.of(new SimpleGrantedAuthority("admin")));
        } else if (userRepo.findByName(username).isPresent()) {
            UserEntity user = userRepo.findByName(username).get();
            return new User(user.getName(), user.getPassword(), List.of(new SimpleGrantedAuthority("user")));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }*/
}
