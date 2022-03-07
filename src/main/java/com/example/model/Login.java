package com.example.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "login")
public class Login {
    @Id
    private String username;
    @NotNull
    private String password;
}
