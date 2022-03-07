package com.example.repository;

import com.example.model.VaccineAppointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineAppointmentRepository extends JpaRepository<VaccineAppointments, Long> {}
