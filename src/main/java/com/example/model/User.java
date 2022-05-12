package com.example.model;

import com.example.security.AttributeEncrypter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String email;

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String name;

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String surname;

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String dateOfBirth;

    @Id
    @Convert(converter = AttributeEncrypter.class)
    private String PPS;

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String address;

    //@Convert(converter = AttributeEncrypter.class)
    private int phoneNumber;

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String nationality;

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String gender;

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    @NotBlank
    @Convert(converter = AttributeEncrypter.class)
    private String vaccineType = "pfizer";

    private int vaccinationStage = 0;

    public User(){
        super();
    }
    public User(String email, @NotBlank String name, @NotBlank String surname, @NotBlank String dateOfBirth, @NotBlank String PPS, @NotBlank String address, int phoneNumber, @NotBlank String nationality, @NotBlank String gender) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.PPS = PPS;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nationality = nationality;
        this.gender = gender;
        this.vaccineType = "Pfizer";
    }

    public User(String PPS, String nationality, String gender, int vaccinationStage){
        this.email = "doesntmatter@gmail.com";
        this.name = "Lukasz";
        this.surname = "Filanowski";
        this.dateOfBirth = "1999-01-20";
        this.PPS = PPS;
        this.address = "Whatever, whatever street";
        this.phoneNumber = 011321234;
        this.nationality = nationality;
        this.gender = gender;
        this.vaccinationStage = vaccinationStage;
        this.vaccineType = "Moderna";
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPPS() {
        return PPS;
    }

    public void setPPS(String PPS) {
        this.PPS = PPS;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getVaccinationStage() {
        return vaccinationStage;
    }

    public void setVaccinationStage(int vaccinationStage) {
        this.vaccinationStage = vaccinationStage;
    }
}
