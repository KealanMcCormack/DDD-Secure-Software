package com.example.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="vaccineappointments")
public class VaccineAppointments {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    @NotBlank
    public String centre;

    @NotBlank
    public String time;

    @NotBlank
    public String date;

    @NotBlank
    public boolean isBooked;

    //TODO: Make this a foreign key to a user
    public String username;

}
