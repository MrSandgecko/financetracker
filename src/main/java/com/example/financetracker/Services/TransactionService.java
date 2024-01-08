package com.example.financetracker.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financetracker.Models.TransactionEnt;
import com.example.financetracker.Repositories.TransactionRepo;


@Service
public class TransactionService {

    private TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo){
        this.transactionRepo = transactionRepo;
    }

    public List<TransactionEnt> getAllTransactions(){
        return transactionRepo.findAll();
    }

    public void saveTransaction(TransactionEnt transactionEnt){
        transactionRepo.save(transactionEnt);
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepo.deleteById(transactionId);
    }
    
}
