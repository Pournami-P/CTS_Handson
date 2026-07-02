package com.cognizant.loan.controller;

import com.cognizant.loan.dto.LoanDTO;
import com.cognizant.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{number}")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable String number) {
        LoanDTO loanDTO = loanService.getLoanByNumber(number);
        return ResponseEntity.ok(loanDTO);
    }
}
