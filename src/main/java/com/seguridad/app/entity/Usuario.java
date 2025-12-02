package com.seguridad.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private String username; // PK según instrucción

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // "ADMIN" o "USER"

    // Constructores
    public Usuario() {}

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters y Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}