package com.enomyfinances.repository;

import com.enomyfinances.model.ExchangeRate;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ExchangeRateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ExchangeRate findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency) {
        List<ExchangeRate> result = entityManager.createQuery(
                        "SELECT e FROM ExchangeRate e WHERE e.fromCurrency = :fromCurrency AND e.toCurrency = :toCurrency", ExchangeRate.class)
                .setParameter("fromCurrency", fromCurrency)
                .setParameter("toCurrency", toCurrency)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
