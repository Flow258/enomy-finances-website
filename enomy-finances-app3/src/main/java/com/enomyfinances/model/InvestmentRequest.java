package com.enomyfinances.model;

import lombok.Data;

@Data
public class InvestmentRequest {
    private String investmentType;
    private double initialAmount;
    private double monthlyAmount;

    // Constructor
    public InvestmentRequest() {}

    public InvestmentRequest(String investmentType, double initialAmount, double monthlyAmount) {
        this.investmentType = investmentType;
        this.initialAmount = initialAmount;
        this.monthlyAmount = monthlyAmount;
    }

    // Getters
    public String getInvestmentType() {
        return investmentType;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    // Setters
    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public void setMonthlyAmount(double monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }
}
