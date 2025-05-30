package org.example.simplewebappspring.repository;


import org.example.simplewebappspring.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE admins AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}