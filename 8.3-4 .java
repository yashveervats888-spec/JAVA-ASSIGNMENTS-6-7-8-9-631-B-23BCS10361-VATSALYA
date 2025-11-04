package com.example.bankapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {
    @Autowired
    private AccountDAO dao;

    @Transactional
    public void transferMoney(int fromId, int toId, double amount) {
        Account from = dao.getAccount(fromId);
        Account to = dao.getAccount(toId);

        if (from.getBalance() < amount)
            throw new RuntimeException("Insufficient balance!");

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        dao.updateAccount(from);
        dao.updateAccount(to);

        System.out.println("Transfer Successful!");
    }
}
