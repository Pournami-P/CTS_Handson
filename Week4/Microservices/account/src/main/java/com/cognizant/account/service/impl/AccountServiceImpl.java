package com.cognizant.account.service.impl;

import com.cognizant.account.dto.AccountDTO;
import com.cognizant.account.entity.Account;
import com.cognizant.account.exception.AccountNotFoundException;
import com.cognizant.account.repository.AccountRepository;
import com.cognizant.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO getAccountByNumber(String number) {
        Account account = accountRepository.findByNumber(number)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with number: " + number));
        return new AccountDTO(account.getNumber(), account.getType(), account.getBalance());
    }
}
