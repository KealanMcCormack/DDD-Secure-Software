package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidation {

    public boolean isStringCorrectFormat(String nameString){
        final String NAME_STRING_PATTERN = "/^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð '-]+$/u";
        Pattern pattern = Pattern.compile(NAME_STRING_PATTERN);

        Matcher mather = pattern.matcher(NAME_STRING_PATTERN);
        return mather.matches();
    }

    public boolean isPPSCorrectFormat(String PPS){
        final String PPS_PATTERN = "\\d{7}[A-Z]{1,2}";
        Pattern pattern = Pattern.compile(PPS_PATTERN);

        Matcher mather = pattern.matcher(PPS);
        return mather.matches();
    }

    public boolean isEmailCorrectFormat(String email){
        final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        Matcher mather = pattern.matcher(email);
        return mather.matches();
    }

    public boolean isPasswordStrong(String password){
        final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,32})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

// (?=.*[a-z])   The string must contain at least 1 lowercase alphabetical character
// (?=.*[A-Z])	The string must contain at least 1 uppercase alphabetical character
// (?=.*[0-9])	The string must contain at least 1 numeric character
// (?=.*[!@#$%^&*])	The string must contain at least one special character, but we are escaping reserved RegEx characters to avoid conflict
// (?=.{8,})	The string must be eight characters or longer
    }
}
