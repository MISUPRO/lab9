/*
package org.example.simplewebappspring.controller;

import org.example.simplewebappspring.model.RegionEntity;
import org.example.simplewebappspring.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    // Получить все регионы
    @GetMapping
    public List<RegionEntity> getAllRegions() {
        return regionRepository.findAll();
    }

    // Получить регион по ID
    @GetMapping("/{id}")
    public ResponseEntity<RegionEntity> getRegionById(@PathVariable int id) {
        Optional<RegionEntity> region = regionRepository.findById(id);
        if (region.isPresent()) {
            return ResponseEntity.ok(region.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Создать новый регион
    @PostMapping
    public RegionEntity createRegion(@RequestBody RegionEntity region) {
        regionRepository.resetAutoIncrement();
        return regionRepository.save(region);
    }

    // Обновить регион
    @PutMapping("/{id}")
    public ResponseEntity<RegionEntity> updateRegion(@PathVariable int id, @RequestBody RegionEntity regionDetails) {
        Optional<RegionEntity> region = regionRepository.findById(id);
        if (region.isPresent()) {
            RegionEntity updatedRegion = region.get();
            updatedRegion.setRegion_name(regionDetails.getRegion_name());
            return ResponseEntity.ok(regionRepository.save(updatedRegion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить регион
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable int id) {
        Optional<RegionEntity> region = regionRepository.findById(id);
        if (region.isPresent()) {
            regionRepository.delete(region.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
*/
