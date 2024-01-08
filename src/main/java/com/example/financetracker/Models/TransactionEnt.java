package com.example.financetracker.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private String item;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    private UserEnt userEnt; 

    public void setTransactionUserEnt(UserEnt userEnt) {
        this.userEnt = userEnt;
    }

     public TransactionEnt(){
        
    }
    
    public TransactionEnt(String item, double amount){
        this.item = item;
        this.amount = amount;
    }

    public String getItem(){
        return this.item;
    }

     public double getAmount(){
        return this.amount;
    }

    public Long getId(){
        return this.id;
    }
    
    
}
