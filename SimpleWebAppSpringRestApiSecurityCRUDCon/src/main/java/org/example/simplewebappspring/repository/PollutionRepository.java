package org.example.simplewebappspring.repository;

import org.example.simplewebappspring.model.PollutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PollutionRepository extends JpaRepository<PollutionEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE pollution AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
    List<PollutionEntity> findByDeletedFalse();
}
