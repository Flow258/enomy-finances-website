package com.enomyfinances.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentResult {
    private int years;
    private double totalInvested;
    private double minReturn;
    private double maxReturn;
    private double fees;
    private double minTax;
    private double maxTax;



    // Constructor
    public InvestmentResult() {}

    public InvestmentResult(int years, double totalInvested, double minReturn, double maxReturn, double fees, double minTax, double maxTax) {
        this.years = years;
        this.totalInvested = totalInvested;
        this.minReturn = minReturn;
        this.maxReturn = maxReturn;
        this.fees = fees;
        this.minTax = minTax;
        this.maxTax = maxTax;
    }

    // Getters
    public int getYears() {
        return years;
    }

    public double getTotalInvested() {
        return totalInvested;
    }

    public double getMinReturn() {
        return minReturn;
    }

    public double getMaxReturn() {
        return maxReturn;
    }

    public double getFees() {
        return fees;
    }

    public double getMinTax() {
        return minTax;
    }

    public double getMaxTax() {
        return maxTax;
    }

    // Setters
    public void setYears(int years) {
        this.years = years;
    }

    public void setTotalInvested(double totalInvested) {
        this.totalInvested = totalInvested;
    }

    public void setMinReturn(double minReturn) {
        this.minReturn = minReturn;
    }

    public void setMaxReturn(double maxReturn) {
        this.maxReturn = maxReturn;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public void setMinTax(double minTax) {
        this.minTax = minTax;
    }

    public void setMaxTax(double maxTax) {
        this.maxTax = maxTax;
    }
}

