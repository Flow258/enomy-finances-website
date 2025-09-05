package com.enomyfinances.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {

    // Simulated exchange rates (replace with actual API integration)
    private static final Map<String, BigDecimal> exchangeRates = new HashMap<>();

    static {
        // USD conversions
        exchangeRates.put("USD_GBP", new BigDecimal("0.78"));
        exchangeRates.put("USD_EUR", new BigDecimal("0.92"));
        exchangeRates.put("USD_BRL", new BigDecimal("5.25"));
        exchangeRates.put("USD_JPY", new BigDecimal("145.50"));
        exchangeRates.put("USD_TRY", new BigDecimal("30.00"));
        
        // GBP conversions
        exchangeRates.put("GBP_USD", new BigDecimal("1.28"));
        exchangeRates.put("GBP_EUR", new BigDecimal("1.18"));
        exchangeRates.put("GBP_BRL", new BigDecimal("6.70"));
        exchangeRates.put("GBP_JPY", new BigDecimal("186.50"));
        exchangeRates.put("GBP_TRY", new BigDecimal("38.50"));
        
        // EUR conversions
        exchangeRates.put("EUR_USD", new BigDecimal("1.09"));
        exchangeRates.put("EUR_GBP", new BigDecimal("0.85"));
        exchangeRates.put("EUR_BRL", new BigDecimal("5.75"));
        exchangeRates.put("EUR_JPY", new BigDecimal("158.00"));
        exchangeRates.put("EUR_TRY", new BigDecimal("32.00"));
        
        // BRL conversions
        exchangeRates.put("BRL_USD", new BigDecimal("0.19"));
        exchangeRates.put("BRL_GBP", new BigDecimal("0.15"));
        exchangeRates.put("BRL_EUR", new BigDecimal("0.17"));
        exchangeRates.put("BRL_JPY", new BigDecimal("27.50"));
        exchangeRates.put("BRL_TRY", new BigDecimal("5.50"));
        
        // JPY conversions
        exchangeRates.put("JPY_USD", new BigDecimal("0.0069"));
        exchangeRates.put("JPY_GBP", new BigDecimal("0.0054"));
        exchangeRates.put("JPY_EUR", new BigDecimal("0.0063"));
        exchangeRates.put("JPY_BRL", new BigDecimal("0.036"));
        exchangeRates.put("JPY_TRY", new BigDecimal("0.20"));
        
        // TRY conversions
        exchangeRates.put("TRY_USD", new BigDecimal("0.033"));
        exchangeRates.put("TRY_GBP", new BigDecimal("0.026"));
        exchangeRates.put("TRY_EUR", new BigDecimal("0.031"));
        exchangeRates.put("TRY_BRL", new BigDecimal("0.18"));
        exchangeRates.put("TRY_JPY", new BigDecimal("5.00"));
    }

    public BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) {
        BigDecimal feePercentage;
        if (amount.compareTo(new BigDecimal("2500")) > 0) {
            feePercentage = new BigDecimal("0.015"); // 1.5%
        } else if (amount.compareTo(new BigDecimal("1500")) > 0) {
            feePercentage = new BigDecimal("0.02"); // 2.0%
        } else if (amount.compareTo(new BigDecimal("500")) > 0) {
            feePercentage = new BigDecimal("0.027"); // 2.7%
        } else {
            feePercentage = new BigDecimal("0.035"); // 3.5%
        }

        BigDecimal feeAmount = amount.multiply(feePercentage);
        BigDecimal netAmount = amount.subtract(feeAmount);
        
        String exchangeKey = fromCurrency + "_" + toCurrency;
        BigDecimal exchangeRate = exchangeRates.getOrDefault(exchangeKey, BigDecimal.ONE);
        
        BigDecimal convertedAmount = netAmount.multiply(exchangeRate);
        
        System.out.println("Original Amount: " + amount);
        System.out.println("Fee Percentage: " + feePercentage.multiply(new BigDecimal("100")) + "%");
        System.out.println("Fee Amount: " + feeAmount);
        System.out.println("Net Amount After Fee: " + netAmount);
        System.out.println("Exchange Rate Used: " + exchangeRate);
        System.out.println("Converted Amount: " + convertedAmount);

        return convertedAmount;
    }
}
