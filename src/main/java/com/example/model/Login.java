package com.example.model;

import lombok.Data;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "login")
public class Login {
    @Id
    private String username;
    @NotNull
    private String password;

    public Login(){}
    public Login(String username, String password, String PPS) {
        this.username = username;
        this.password = password;
        this.PPS = PPS;
    }

    @NotNull
    private String PPS;
}
