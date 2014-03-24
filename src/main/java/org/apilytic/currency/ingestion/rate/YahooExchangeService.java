package org.apilytic.currency.ingestion.rate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.YahooFinanceManager;
import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Sync currencies to database.
 * 
 * @author Georgi Lambov
 * 
 */
@Service
public class YahooExchangeService implements RateIngestion {

	@Autowired
	private YahooFinanceManager yahooFinanaceManager;

	@Autowired
	private YahooQueryRateBuilder queryRateBuilder;

	@Autowired
	private RateRepository rateRepo;

	private List<Rate> rates;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apilytic.currency.ingestion.rate.RateIngestion#sync()
	 */
	@Override
	public void sync() {
		String createQueryRate = queryRateBuilder.createQueryRate();

		// TODO split query rate and execute it in batch
		yahooFinanaceManager.setExchangeQuery(createQueryRate);
		List<? extends ExchangeRate> providedRates = yahooFinanaceManager
				.provideRate();

		rates = new ArrayList<Rate>();

		for (ExchangeRate exchangeRate : providedRates) {
			Map<String, String> values = new HashMap<String, String>();
			values.put(exchangeRate.toCurrency(), exchangeRate.rate());

			Rate r = new Rate();
			r.setKey(exchangeRate.fromCurrency());
			r.setValue(values);

			rates.add(r);
		}

		// FIXME implementaiton of repo save
		rateRepo.save(rates);
	}
}