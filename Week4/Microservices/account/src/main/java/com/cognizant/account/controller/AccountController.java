package com.cognizant.account.controller;

import com.cognizant.account.dto.AccountDTO;
import com.cognizant.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{number}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable String number) {
        AccountDTO accountDTO = accountService.getAccountByNumber(number);
        return ResponseEntity.ok(accountDTO);
    }
}
