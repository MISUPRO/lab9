package org.example.simplewebappspring.repository;

import org.example.simplewebappspring.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE users AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
