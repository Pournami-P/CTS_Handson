package com.cognizant.loan.service;

import com.cognizant.loan.dto.LoanDTO;

public interface LoanService {
    LoanDTO getLoanByNumber(String number);
}
