package com.example.security;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidation {

    public boolean isDateCorrectFormat(long timeBetween){

        if(timeBetween < 6575) {
            return false;
        }

        return true;
    }

    public boolean isAddressCorrectFormat(String address){
        final String ADDRESS_PATTERN = "^[a-zA-Z0-9 ',/-]{10,100}$";
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);

        Matcher mather = pattern.matcher(address);
        return mather.matches();
    }

    public boolean isPhoneNumberCorrectFormat(String phoneNumber){
        final String PHONE_PATTERN = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[\\s0-9]*$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);

        Matcher mather = pattern.matcher(phoneNumber);

        if(phoneNumber.length() > 20){
            return false;
        }

        return mather.matches();
    }

    public boolean isStringCorrectFormat(String nameString){
        final String NAME_STRING_PATTERN = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð '-]+$";
        Pattern pattern = Pattern.compile(NAME_STRING_PATTERN);

        Matcher mather = pattern.matcher(nameString);

        if(nameString.length() > 50){
            return false;
        }

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
    }
}
