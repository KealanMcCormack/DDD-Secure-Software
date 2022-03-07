package com.example.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="vaccineappointments")
public class VaccineAppointment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    @NotBlank
    public String centre;

    @NotBlank
    public String time;

    @NotBlank
    public String date;

    public boolean isBooked;

    //TODO: Make this a foreign key to a user
    public String username;

    public VaccineAppointment(int id, String centre, String time, String date, boolean isBooked, String username) {
        this.id = id;
        this.centre = centre;
        this.time = time;
        this.date = date;
        this.isBooked = isBooked;
        this.username = username;
    }

    public VaccineAppointment(int id, String centre, String time, String date, boolean isBooked) {
        this.id = id;
        this.centre = centre;
        this.time = time;
        this.date = date;
        this.isBooked = isBooked;
    }

    public VaccineAppointment() {

    }
}
