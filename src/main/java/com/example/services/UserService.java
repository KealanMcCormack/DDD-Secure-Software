package com.example.services;

import com.example.model.ConfirmationToken;
import com.example.model.Login;

public interface UserService {
    void save(Login user);

    Login findByUsername(String username);

    Login findByEmail(String email);

    void save(ConfirmationToken confirmationToken);

    Login isValidToken(String confirmationToken);

    void savePassword(String email, String password);

}
