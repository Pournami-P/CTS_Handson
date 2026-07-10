package com.cognizant.loan.service.impl;

import com.cognizant.loan.dto.LoanDTO;
import com.cognizant.loan.entity.Loan;
import com.cognizant.loan.exception.LoanNotFoundException;
import com.cognizant.loan.repository.LoanRepository;
import com.cognizant.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public LoanDTO getLoanByNumber(String number) {
        Loan loan = loanRepository.findByNumber(number)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with number: " + number));
        return new LoanDTO(loan.getNumber(), loan.getType(), loan.getLoan(), loan.getEmi(), loan.getTenure());
    }
}
