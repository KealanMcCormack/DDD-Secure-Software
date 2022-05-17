package com.example.services;

import com.example.model.ConfirmationToken;
import com.example.model.Login;
import com.example.repository.ConfirmationTokenRepository;
import com.example.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Override
    public void save(Login user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        loginRepository.save(user);
    }

    @Override
    public Login findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    @Override
    public Login findByEmail(String email) {
        return null;
        //return loginRepository.findByEmail(email);
    }

    @Override
    public void save(ConfirmationToken token) {

        // Save it
        confirmationTokenRepository.save(token);
    }

    @Override
    public Login isValidToken(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            if(!isTokenExpired(token.getCreatedDate()) && !token.isUsed()) {
                //User user = loginRepository.findByEmail(token.getUser().getEmail());
                Login login = loginRepository.findByUsername(token.getLogin().getUsername());
                token.setUsed(true);
                confirmationTokenRepository.save(token);


                return login;
            }
        }
        return null;
    }

    @Override
    public void savePassword(String username, String password) {
        Login tokenUser = loginRepository.findByUsername(username);
        tokenUser.setPassword(bCryptPasswordEncoder.encode(password));
        loginRepository.save(tokenUser);
    }



    private boolean isTokenExpired(Date createdDate){
        Date now = new Date();

        long milliseconds1 = createdDate.getTime();
        long milliseconds2 = now.getTime();
        long diffMinutes = (milliseconds2 - milliseconds1)/ (60 * 1000);
        if(diffMinutes <= 20 ) return false;


        return true;
    }
}
