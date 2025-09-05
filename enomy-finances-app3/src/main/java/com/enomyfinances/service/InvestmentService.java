package com.enomyfinances.service;

import org.springframework.stereotype.Service;

import com.enomyfinances.model.InvestmentPlan;
import com.enomyfinances.model.InvestmentRequest;
import com.enomyfinances.model.InvestmentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InvestmentService {
    private static final Map<String, InvestmentPlan> INVESTMENT_PLANS = Map.of(
        "basic", new InvestmentPlan(0.012, 0.024, 0.0025, 50, 0, 20000),
        "plus", new InvestmentPlan(0.03, 0.055, 0.003, 50, 300, 30000),
        "managed", new InvestmentPlan(0.04, 0.23, 0.013, 150, 1000, Double.MAX_VALUE)
    );

    public List<InvestmentResult> calculateInvestment(InvestmentRequest request) {
        List<InvestmentResult> results = new ArrayList<>();
        InvestmentPlan plan = INVESTMENT_PLANS.get(request.getInvestmentType());
        
        if (plan == null) {
            throw new IllegalArgumentException("Invalid investment type");
        }

        // Calculate for 1, 5, and 10 years
        for (int years : new int[]{1, 5, 10}) {
            results.add(calculateForPeriod(request, plan, years));
        }
        
        return results;
    }

    private InvestmentResult calculateForPeriod(InvestmentRequest request, InvestmentPlan plan, int years) {
        int months = years * 12;
        double totalInvested = request.getInitialAmount() + (request.getMonthlyAmount() * months);
        
        double minReturn = calculateCompoundReturn(request.getInitialAmount(), 
                                                 request.getMonthlyAmount(), 
                                                 plan.getMinRate(), 
                                                 months);
        
        double maxReturn = calculateCompoundReturn(request.getInitialAmount(), 
                                                 request.getMonthlyAmount(), 
                                                 plan.getMaxRate(), 
                                                 months);
        
        double fees = totalInvested * plan.getFeeRate() * months;
        
        InvestmentResult result = new InvestmentResult();
        result.setYears(years);
        result.setTotalInvested(totalInvested);
        result.setMinReturn(minReturn - fees);
        result.setMaxReturn(maxReturn - fees);
        result.setFees(fees);
        result.setMinTax(calculateTax(request.getInvestmentType(), minReturn - totalInvested));
        result.setMaxTax(calculateTax(request.getInvestmentType(), maxReturn - totalInvested));
        
        return result;
    }

    private double calculateCompoundReturn(double initial, double monthly, double rate, int months) {
        double monthlyRate = rate / 12;
        double balance = initial;
        
        for (int i = 0; i < months; i++) {
            balance = (balance + monthly) * (1 + monthlyRate);
        }
        
        return balance;
    }

    private double calculateTax(String type, double profit) {
        if ("basic".equals(type)) return 0;
        
        if ("plus".equals(type)) {
            return profit > 12000 ? (profit - 12000) * 0.1 : 0;
        }
        
        if ("managed".equals(type)) {
            double tax = 0;
            if (profit > 40000) {
                tax += (profit - 40000) * 0.2;
                tax += (40000 - 12000) * 0.1;
            } else if (profit > 12000) {
                tax += (profit - 12000) * 0.1;
            }
            return tax;
        }
        
        return 0;
    }
}