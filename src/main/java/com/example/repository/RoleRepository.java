package com.example.repository;

import com.example.model.Role;
import com.example.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
