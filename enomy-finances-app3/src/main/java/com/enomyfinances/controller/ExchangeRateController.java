package com.enomyfinances.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.enomyfinances.service.ExchangeRateService;

@RestController
@RequestMapping("/exchange-rate")
public class ExchangeRateController {
    
    @Autowired
    private ExchangeRateService exchangeRateService;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getExchangeRate(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam BigDecimal amount) {
        
        try {
            // Validate input parameters
            if (fromCurrency == null || fromCurrency.trim().isEmpty()) {
                return createErrorResponse("From currency is required");
            }
            if (toCurrency == null || toCurrency.trim().isEmpty()) {
                return createErrorResponse("To currency is required");
            }
            if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                return createErrorResponse("Amount must be greater than zero");
            }
            if (fromCurrency.equals(toCurrency)) {
                return createErrorResponse("From and to currencies must be different");
            }
            
            // Calculate fees first (on original amount)
            BigDecimal feePercentage = getFeePercentage(amount);
            BigDecimal feeAmount = amount.multiply(feePercentage).setScale(2, RoundingMode.HALF_UP);
            BigDecimal netAmount = amount.subtract(feeAmount).setScale(2, RoundingMode.HALF_UP);
            
            // Convert the net amount (after fees)
            BigDecimal convertedAmount = exchangeRateService.convert(fromCurrency, toCurrency, netAmount);
            
            Map<String, Object> response = new HashMap<>();
            response.put("originalAmount", amount.setScale(2, RoundingMode.HALF_UP));
            response.put("feeAmount", feeAmount);
            response.put("netAmount", netAmount);
            response.put("convertedAmount", convertedAmount.setScale(2, RoundingMode.HALF_UP));
            response.put("toCurrency", toCurrency);
            response.put("fromCurrency", fromCurrency);
            response.put("feePercentage", feePercentage.multiply(new BigDecimal("100")).setScale(1, RoundingMode.HALF_UP)); // For display purposes
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            return createErrorResponse("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return createErrorResponse("Failed to calculate exchange rate: " + e.getMessage());
        }
    }
    
    /**
     * Helper method for fee calculation based on amount tiers
     * @param amount The original amount to exchange
     * @return The fee percentage as BigDecimal
     */
    private BigDecimal getFeePercentage(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("2500")) > 0) {
            return new BigDecimal("0.015"); // 1.5% for amounts > 2500
        } else if (amount.compareTo(new BigDecimal("1500")) > 0) {
            return new BigDecimal("0.020"); // 2.0% for amounts > 1500
        } else if (amount.compareTo(new BigDecimal("500")) > 0) {
            return new BigDecimal("0.027"); // 2.7% for amounts > 500
        } else {
            return new BigDecimal("0.035"); // 3.5% for amounts <= 500
        }
    }
    
    /**
     * Helper method to create error response
     * @param message Error message
     * @return ResponseEntity with error response
     */
    private ResponseEntity<Map<String, Object>> createErrorResponse(String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}