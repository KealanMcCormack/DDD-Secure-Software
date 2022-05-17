package com.example.repository;

import com.example.model.IPs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPRepository extends
        JpaRepository<IPs, String> {}
