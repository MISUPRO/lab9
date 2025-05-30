package org.example.simplewebappspring.controller;

import org.example.simplewebappspring.model.UserEntity;
import org.example.simplewebappspring.model.AdminEntity;
import org.example.simplewebappspring.repository.UserRepository;
import org.example.simplewebappspring.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserRepository userRepo;
    private final AdminRepository adminRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiController(UserRepository userRepo, AdminRepository adminRepo) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Получить список всех пользователей
    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return userRepo.findAll();
    }

    // Получить список всех администраторов
    @GetMapping("/admins")
    public List<AdminEntity> getAdmins() {
        return adminRepo.findAll();
    }

    // Получить пользователя по ID
    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        Optional<UserEntity> user = userRepo.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Получить администратора по ID
    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminEntity> getAdminById(@PathVariable Integer id) {
        Optional<AdminEntity> admin = adminRepo.findById(id);
        return admin.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Регистрация нового пользователя через API
    @PostMapping("/register/user")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity) {
        boolean userExists = userRepo.findByName(userEntity.getName()).isPresent();
        boolean adminExists = adminRepo.findByName(userEntity.getName()).isPresent();

        if (userExists || adminExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);  // Если пользователь или админ уже существует
        }

        // Хэшируем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);

        userRepo.resetAutoIncrement();
        userRepo.save(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }

    // Обновление данных пользователя
    @PutMapping("/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Integer id, @RequestBody UserEntity userEntity) {
        if (!userRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userEntity.setIdUser(id);
        UserEntity updatedUser = userRepo.save(userEntity);
        return ResponseEntity.ok(updatedUser);
    }

    // Обновление данных администратора
    @PutMapping("/admins/{id}")
    public ResponseEntity<AdminEntity> updateAdmin(@PathVariable Integer id, @RequestBody AdminEntity adminEntity) {
        if (!adminRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        adminEntity.setId_admin(id);
        AdminEntity updatedAdmin = adminRepo.save(adminEntity);
        return ResponseEntity.ok(updatedAdmin);
    }

    // Удаление пользователя
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (!userRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Удаление администратора
    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) {
        if (!adminRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        adminRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
