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
    @NotNull
    private String PPS;
}
