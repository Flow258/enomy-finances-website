package com.enomyfinances.model;


public class InvestmentPlan {
    private final double minRate;
    private final double maxRate;
    private final double feeRate;
    private final double minMonthly;
    private final double minInitial;
    private final double maxYearly;

    // Constructor
    public InvestmentPlan(double minRate, double maxRate, double feeRate, double minMonthly, double minInitial, double maxYearly) {
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.feeRate = feeRate;
        this.minMonthly = minMonthly;
        this.minInitial = minInitial;
        this.maxYearly = maxYearly;
    }

    // Getters
    public double getMinRate() {
        return minRate;
    }

    public double getMaxRate() {
        return maxRate;
    }

    public double getFeeRate() {
        return feeRate;
    }

    public double getMinMonthly() {
        return minMonthly;
    }

    public double getMinInitial() {
        return minInitial;
    }

    public double getMaxYearly() {
        return maxYearly;
    }
}
