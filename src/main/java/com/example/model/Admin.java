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

}
