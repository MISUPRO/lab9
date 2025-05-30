package org.example.simplewebappspring.repository;


import org.example.simplewebappspring.model.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE region AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    List<RegionEntity> findByDeletedFalse();
}
