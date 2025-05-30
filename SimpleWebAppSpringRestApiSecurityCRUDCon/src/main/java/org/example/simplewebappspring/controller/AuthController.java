package org.example.simplewebappspring.controller;

import org.example.simplewebappspring.model.UserEntity;
import org.example.simplewebappspring.model.AdminEntity;
import org.example.simplewebappspring.repository.AdminRepository;
import org.example.simplewebappspring.repository.PollutionRepository;
import org.example.simplewebappspring.repository.RegionRepository;
import org.example.simplewebappspring.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;
    private final AdminRepository adminRepo;
    private final PollutionRepository pollutionRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String pass, @RequestParam String mail, Model model) {
        boolean userExists = userRepo.findByName(name).isPresent() || adminRepo.findByName(name).isPresent();

        if (userExists) {
            model.addAttribute("error", "A user with this name already exists");
            return "register";
        }

        String hashedPass = passwordEncoder.encode(pass);

        UserEntity user = new UserEntity();
        user.setName(name);
        user.setPassword(hashedPass);
        user.setEmail(mail);
        user.setRole("user");
        userRepo.resetAutoIncrement();
        userRepo.save(user);
        return "redirect:/";
    }

    /*@GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("regions", regionRepository.findAll());
        return "admin"; // admin.jsp
    }

    @GetMapping("/welcome")
    public String userDashboard(Model model) {
        model.addAttribute("pollutions", pollutionRepository.findAll());
        return "welcome"; // user.jsp
    }*/
}