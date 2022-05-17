package com.example.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "admins")
public class Admin {

    @NotBlank
    private String email;
    @Id
    private String username;
    @NotNull
    private String password;

    private int failedLoginAttempts;

    public Admin(){}

    public Admin(String email, String username, String password, String privilege) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.privilege = privilege;
        failedLoginAttempts = 0;
    }

    @NotNull
    private String privilege;
}
