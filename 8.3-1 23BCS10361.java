package com.example.bankapp;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    private int id;
    private String name;
    private double balance;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
