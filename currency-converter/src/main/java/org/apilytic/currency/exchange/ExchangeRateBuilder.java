package org.apilytic.currency.exchange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Build cross-reference exchange rates.
 *
 * @author Georgi Lambov
 */
@Service
public class ExchangeRateBuilder {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepo;
    private Map<String, Set<CurrencyExchange>> rates;

    private ExchangeRate exchangeRate;

    /**
     * Creates cross reference rates for currencies from currency ISO codes
     * persisted entities.
     */
    protected void buildRates() {
        Set<CurrencyExchange> currencies = currencyExchangeRepo
                .findAllCurencyExchanges();

        rates = new HashMap<>();
        for (CurrencyExchange currency : currencies) {
            Set<CurrencyExchange> providedCurrencies = currencies.stream().collect(Collectors.toSet());
            providedCurrencies.remove(currency);

            rates.put(currency.getTitle(), providedCurrencies);
        }

        exchangeRate.setRates(rates);
    }

    /**
     * Director to call builder for rates.
     */
    public void constructExchageRate() {
        exchangeRate = new ExchangeRate();
        buildRates();
    }

    /**
     * @return
     */
    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

}
