/*
package org.example.simplewebappspring.controller;

import org.example.simplewebappspring.model.PollutionEntity;
import org.example.simplewebappspring.repository.PollutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pollutions")
public class PollutionController {

    @Autowired
    private PollutionRepository pollutionRepository;

    // Получить все загрязнения
    @GetMapping
    public List<PollutionEntity> getAllPollutions() {
        return pollutionRepository.findAll();
    }

    // Получить загрязнение по ID
    @GetMapping("/{id}")
    public ResponseEntity<PollutionEntity> getPollutionById(@PathVariable int id) {
        Optional<PollutionEntity> pollution = pollutionRepository.findById(id);
        if (pollution.isPresent()) {
            return ResponseEntity.ok(pollution.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Создать новое загрязнение
    @PostMapping
    public PollutionEntity createPollution(@RequestBody PollutionEntity pollution) {
        pollutionRepository.resetAutoIncrement();
        return pollutionRepository.save(pollution);
    }

    // Обновить загрязнение
    @PutMapping("/{id}")
    public ResponseEntity<PollutionEntity> updatePollution(@PathVariable int id, @RequestBody PollutionEntity pollutionDetails) {
        Optional<PollutionEntity> pollution = pollutionRepository.findById(id);
        if (pollution.isPresent()) {
            PollutionEntity updatedPollution = pollution.get();
            updatedPollution.setPollution_level(pollutionDetails.getPollution_level());
            updatedPollution.setRegion(pollutionDetails.getRegion());
            return ResponseEntity.ok(pollutionRepository.save(updatedPollution));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить загрязнение
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePollution(@PathVariable int id) {
        Optional<PollutionEntity> pollution = pollutionRepository.findById(id);
        if (pollution.isPresent()) {
            pollutionRepository.delete(pollution.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
*/
