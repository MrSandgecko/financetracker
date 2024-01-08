package com.example.financetracker.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financetracker.Models.UserEnt;


public interface UserRepo extends JpaRepository<UserEnt, Long> {
    Optional<UserEnt> findByUsernameAndPassword(String username, String password);
    Optional<UserEnt> findByUsername(String username);

}
