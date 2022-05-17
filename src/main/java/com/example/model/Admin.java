package com.example.model;

import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.security.AttributeEncrypter;
import org.hibernate.annotations.ColumnTransformer;

@Data
@Entity
@Table(name = "admins")
public class Admin {

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String email;

    @Id
    @Convert(converter = AttributeEncrypter.class)
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
    @Convert(converter = AttributeEncrypter.class)
    private String privilege;

    public void iterateFailedLogin(){
        failedLoginAttempts++;
    }
}
