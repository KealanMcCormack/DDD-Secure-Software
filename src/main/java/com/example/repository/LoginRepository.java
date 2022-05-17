package com.example.repository;

import com.example.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends
        JpaRepository<Login, String> {
    Login findByUsername(String username);
}