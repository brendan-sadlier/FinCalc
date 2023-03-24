package com.fincalc.FinancialCalculator.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompoundCalculation {

    private double principal;
    private double apr;
    private double compound;
    private double time;
    private double total;
    private double totalInterest;
}
