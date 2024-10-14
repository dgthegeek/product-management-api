package com.exemple.productmanagementapi.model;

import jakarta.validation.constraints.NotBlank;

public class AuthenticationRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Constructeur par défaut
    public AuthenticationRequest() {}

    // Constructeur avec tous les champs
    public AuthenticationRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }

    // Getters et setters
    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}