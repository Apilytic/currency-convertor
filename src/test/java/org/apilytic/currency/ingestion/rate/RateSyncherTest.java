package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.parser.RateParser;
import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.domain.YahooChart;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RateSyncherTest {

	@InjectMocks
	private RateSyncher syncher;

	@Mock
	private ExchangeRepository exchangeRepo;
	@Mock
	private CurrencyRepository currencyRepo;
	@Mock
	private RateFetch rateFetcher;
	@Mock
	private RateParser rateParser;
	@Mock
	private Exchange exchange;
	@Mock
	private Currency currency;
	@Mock
	private CurrencyPair pair;

	@Test
	public void syncRatesMultipleCurrencies() {
		Iterable<Currency> i = Arrays.asList(currency);
		String usd = "USD";
		String eur = "EUR";

		YahooChart chart = mock(YahooChart.class);

		when(currencyRepo.findAll()).thenReturn(i);
		when(currency.getCodes()).thenReturn(new HashSet<>(Arrays.asList(usd, eur)));
		when(rateFetcher.fetch(pair)).thenReturn(chart);
		when(rateParser.parse(chart)).thenReturn("1.29");

		syncher.sync();

		assertTrue(syncher instanceof RateSyncher);

		verify(pair).setFrom(eur);
		verify(pair).setFrom(usd);
		verify(pair).setTo(usd);
		verify(pair).setTo(eur);

		Map rates = new HashMap() {{
			put("EUR", "1.29");
		}};

		verify(exchange).setRates(rates);
		verify(exchange).setId(usd);

		verify(exchangeRepo).save(Arrays.asList(exchange, exchange));
	}
}