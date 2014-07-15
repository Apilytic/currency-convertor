package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeRate;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Query currency exchange rate from stored rates updated periodically.
 * This is fast operation.
 *
 * <ul>
 * <li>Yahoo finance</li>
 * <li>Google finance calculator</li>
 * </ul>
 *
 * Created by g on 7/11/14.
 */
public class KeyStoreExchangeApi implements CurrencyExchangeApi {

    @Autowired
    private RateRepository rateRepo;

    @Override
    public CurrencyRate exchangeSingleRate(ExchangeRate exchangeRate) {
        Rate rate = rateRepo.findOne(Rate.key(exchangeRate.getFromCurrency()));

        String rawRatio = rate.getValue().get(exchangeRate.getToCurrency());

        BigDecimal ratio = new BigDecimal(rawRatio);
        BigDecimal convertedAmount = ratio.multiply(new BigDecimal(exchangeRate.getAmount()));

        String exchange = convertedAmount.setScale(2, RoundingMode.HALF_EVEN).toString();

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setExchange(exchange);
        return currencyRate;
    }

    @Override
    public List<CurrencyRate> exchangeMultipleRates(List<ExchangeRate> exchangeRates) {
        return exchangeRates.stream().map(this::exchangeSingleRate).collect(Collectors.toList());
    }
}