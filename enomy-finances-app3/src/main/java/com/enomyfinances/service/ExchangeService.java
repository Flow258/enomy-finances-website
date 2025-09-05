package com.enomyfinances.service;

import com.enomyfinances.model.ExchangeRate;
import com.enomyfinances.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount) {
        ExchangeRate rate = exchangeRateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        if (rate == null) {
            throw new RuntimeException("Exchange rate not found for " + fromCurrency + " to " + toCurrency);
        }
        return amount.multiply(rate.getRate());
    }
}
