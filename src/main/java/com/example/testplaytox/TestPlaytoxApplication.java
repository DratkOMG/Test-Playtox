package com.example.testplaytox;

import com.example.testplaytox.models.Account;
import com.example.testplaytox.services.AccountService;
import com.example.testplaytox.services.TransferService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TestPlaytoxApplication {

    private static final int ACCOUNT_COUNT = 10;
    private static final int TRANSACTION_THRESHOLD = 30;
    private static final int THREAD_COUNT = 9;
    private static final int MONEY = 10000;
    private static volatile int transactionCount = 0;

    public static void main(String[] args) {
        SpringApplication.run(TestPlaytoxApplication.class, args);

        List<Account> accounts = createAccounts();

        AccountService accountService = new AccountService();
        TransferService transferService = new TransferService(accountService);

        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                while (transactionCount < TRANSACTION_THRESHOLD) {
                    Account from = accounts.get(ThreadLocalRandom.current().nextInt(accounts.size()));
                    Account to = accounts.get(ThreadLocalRandom.current().nextInt(accounts.size()));
                    while (to == from) {
                        to = accounts.get(ThreadLocalRandom.current().nextInt(accounts.size()));
                    }

                    int amount = ThreadLocalRandom.current().nextInt(1, 10000);
                    transferService.transfer(from, to, amount);

                    synchronized (TestPlaytoxApplication.class) {
                        transactionCount++;
                    }

                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }).start();
        }
    }

    private static List<Account> createAccounts() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < ACCOUNT_COUNT; i++) {
            accounts.add(Account.builder()
                    .id((long) i)
                    .money(MONEY)
                    .build());
        }
        return accounts;
    }
}
