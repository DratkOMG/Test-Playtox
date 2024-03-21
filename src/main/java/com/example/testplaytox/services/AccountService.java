package com.example.testplaytox.services;

import com.example.testplaytox.models.Account;

/**
 * @author DratkOMG
 * @created 17:10 - 21/03/2024
 */
public class AccountService {

    public synchronized boolean transfer(Account from, Account to, int amount) {
        if (amount > 0 && from.getMoney() >= amount) {
            from.setMoney(from.getMoney() - amount);
            to.setMoney(to.getMoney() + amount);
            return true;
        }
        return false;
    }

}
