package org.example.simplewebappspring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "UT")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;  // Автоинкрементный идентификатор

    @Column(nullable = false)//, unique = true)  // Уникальное имя пользователя
    private String name;

    @Column(nullable = false)  // Пароль должен быть обязательным
    private String password;

    @Column(nullable = false)//, unique = true)  // Уникальный email пользователя
    private String email;

    @Column(nullable = false)  // Роль по умолчанию - "user"
    private String role = "user";

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    // Конструктор по умолчанию
    public UserEntity() {}

    // Конструктор с параметрами (по желанию)
    public UserEntity(String name, String password, String email, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Геттеры и сеттеры
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
