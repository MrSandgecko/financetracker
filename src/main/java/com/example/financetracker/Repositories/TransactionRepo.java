package com.example.financetracker.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financetracker.Models.TransactionEnt;

public interface TransactionRepo extends JpaRepository<TransactionEnt, Long> {
    
}
