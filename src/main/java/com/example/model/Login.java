package com.example.model;

import lombok.Data;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "login")
public class Login {
    @Id
    @NotNull
    private String username;
    @NotNull
    private String password;

    @NotNull
    private String PPS;

    private int failedLoginAttempts;

    public Login(){}
    public Login(String username, String password, String PPS) {
        this.username = username;
        this.password = password;
        this.PPS = PPS;
        failedLoginAttempts = 0;
    }

    public void iterateFailedLogin(){
        failedLoginAttempts++;
    }
}
