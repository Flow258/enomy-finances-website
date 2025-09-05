package com.enomyfinances.controller;

import com.enomyfinances.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController // Ensures JSON response
@RequestMapping("/api") // Base path for APIs
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping(value = "/convert", produces = "application/json")
    public Map<String, Object> convert(
            @RequestParam String from, 
            @RequestParam String to, 
            @RequestParam BigDecimal amount) {

        BigDecimal result = exchangeService.convertCurrency(from, to, amount);

        Map<String, Object> response = new HashMap<>();
        response.put("originalAmount", amount);
        response.put("feePercentage", "3.5%");
        response.put("feeAmount", amount.multiply(new BigDecimal("0.035")));
        response.put("netAmount", amount.multiply(new BigDecimal("0.965")));
        response.put("exchangeRate", new BigDecimal("0.92")); // Example rate
        response.put("convertedAmount", result);
        response.put("toCurrency", to);

        return response; // Returns JSON response
    }
}
