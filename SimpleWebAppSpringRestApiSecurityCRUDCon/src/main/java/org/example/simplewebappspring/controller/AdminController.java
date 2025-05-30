package org.example.simplewebappspring.controller;

import lombok.RequiredArgsConstructor;
import org.example.simplewebappspring.model.AdminEntity;
import org.example.simplewebappspring.model.PollutionEntity;
import org.example.simplewebappspring.model.RegionEntity;
import org.example.simplewebappspring.model.UserEntity;
import org.example.simplewebappspring.repository.AdminRepository;
import org.example.simplewebappspring.repository.PollutionRepository;
import org.example.simplewebappspring.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.example.simplewebappspring.repository.UserRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepo;
    private final RegionRepository regionRepo;
    private final PollutionRepository pollutionRepo;
    private final PasswordEncoder passwordEncoder;



    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("regions", regionRepo.findByDeletedFalse());
        return "admin";
    }

    @PostMapping("/admin/region/add")
    public String addRegion(@RequestParam String region_name) {
        RegionEntity region = new RegionEntity();
        region.setRegion_name(region_name);
        regionRepo.save(region);
        return "redirect:/admin";
    }

    @PostMapping("/admin/region/edit")
    public String editRegion(@RequestParam int id, @RequestParam String region_name) {
        RegionEntity region = regionRepo.findById(id).orElseThrow();
        region.setRegion_name(region_name);
        regionRepo.save(region);
        return "redirect:/admin";
    }

    @PostMapping("/admin/region/delete")
    public String deleteRegion(@RequestParam int id) {
        RegionEntity region = regionRepo.findById(id).orElseThrow();
        region.setDeleted(true);
        regionRepo.save(region);
        return "redirect:/admin";
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        List<RegionEntity> regions = regionRepo.findAll(); // Получаем все регионы из БД
        List<PollutionEntity> pollutions = pollutionRepo.findAll(); // Получаем данные загрязнений
        model.addAttribute("regions", regions);
        model.addAttribute("pollutions", pollutions);
        return "welcome"; // имя thymeleaf-шаблона (welcome.html)
    }

    @PostMapping("/welcome/pollution/add")
    public String addPollution(@RequestParam String pollution_level, @RequestParam int region_id) {
        RegionEntity region = regionRepo.findById(region_id).orElseThrow();
        PollutionEntity pollution = new PollutionEntity();
        pollution.setPollution_level(pollution_level);
        pollution.setRegion(region);
        pollutionRepo.save(pollution);
        return "redirect:/welcome";
    }

    @PostMapping("/welcome/pollution/edit")
    public String editPollution(@RequestParam int id, @RequestParam String pollution_level) {
        PollutionEntity pollution = pollutionRepo.findById(id).orElseThrow();
        pollution.setPollution_level(pollution_level);
        pollutionRepo.save(pollution);
        return "redirect:/welcome";
    }

    @PostMapping("/welcome/pollution/delete")
    public String deletePollution(@RequestParam int id) {
        PollutionEntity pollution = pollutionRepo.findById(id).orElseThrow();
        pollution.setDeleted(true);
        pollutionRepo.save(pollution);
        return "redirect:/welcome";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("regions", regionRepo.findAll());
        model.addAttribute("users", userRepo.findAll());
        return "admin";
    }

    @PostMapping("/admin/user/add")
    public String addUser(@RequestParam String name,@RequestParam String email,@RequestParam String password, @RequestParam String role) {
        UserEntity user = new UserEntity();
        String hashedPass = passwordEncoder.encode(password);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashedPass); // лучше захэшировать!
        user.setRole(role);
        userRepo.save(user);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/user/edit")
    public String editUser(@RequestParam int id, @RequestParam String name,@RequestParam String email, @RequestParam String role) {
        UserEntity user = userRepo.findById(id).orElseThrow();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        userRepo.save(user);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@RequestParam int id) {
        UserEntity user = userRepo.findById(id).orElseThrow();
        user.setDeleted(true);
        userRepo.save(user);
        return "redirect:/admin/dashboard";
    }
}
