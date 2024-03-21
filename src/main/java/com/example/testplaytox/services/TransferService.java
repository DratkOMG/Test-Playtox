package com.example.testplaytox.services;

import com.example.testplaytox.exception.BadRequestException;
import com.example.testplaytox.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author DratkOMG
 * @created 16:36 - 20/03/2024
 */
public class TransferService {
    private static final Logger logger = LogManager.getLogger(TransferService.class);

    private final AccountService accountService;

    public TransferService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void transfer(Account from, Account to, int amount) {
        if (accountService.transfer(from, to, amount)) {
            logTransfer(from, to, amount);
        } else {
            String mess = String.format("You cant transfer %d, you only have %d", amount, from.getMoney());
            logError(new BadRequestException(mess, 400));
        }
    }

    private void logTransfer(Account from, Account to, int amount) {
        String message = String.format("Transfer %d from %s to %s (%s have %d, %s have %d))", amount, from.getId(), to.getId(), from.getId(), from.getMoney(), to.getId(), to.getMoney());
        logger.info(message);
    }

    private void logError(Exception e) {
        logger.error("An error occurred: " + e.getMessage(), e);
    }
}
