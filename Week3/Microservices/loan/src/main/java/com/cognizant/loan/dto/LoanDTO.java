package com.cognizant.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
    private String number;
    private String type;
    private Double loan;
    private Double emi;
    private Integer tenure;
}
