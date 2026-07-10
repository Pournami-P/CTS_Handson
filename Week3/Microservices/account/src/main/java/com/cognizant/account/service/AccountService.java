package com.cognizant.account.service;

import com.cognizant.account.dto.AccountDTO;

public interface AccountService {
    AccountDTO getAccountByNumber(String number);
}
