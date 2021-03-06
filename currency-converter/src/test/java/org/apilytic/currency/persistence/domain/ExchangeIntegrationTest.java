package org.apilytic.currency.persistence.domain;

import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Rollback
public class ExchangeIntegrationTest {

	@Autowired
	private ExchangeRepository repo;

	@Test
	public void save() {
		Map h = new HashMap() {{
			put("USD", "1.9");
			put("EUR", "1.8");
		}};

		Exchange e = new Exchange();
		e.setRates(h);
		repo.save(e);


		Map rates = repo.findById(e.getId()).get().getRates();
		assertEquals(2, rates.size());
	}

	@Test
	public void saveWithCustomId() {
		Map h = new HashMap() {{
			put("USD", "1.9");
		}};

		Exchange e = new Exchange();
		e.setRates(h);
		e.setId("JPY-1");

		repo.save(e);

		Map rates = repo.findById(e.getId()).get().getRates();
		assertEquals(1, rates.size());
	}

	@Test
	public void findAll() {
		Iterable<Exchange> all = repo.findAll();
		all.forEach(exchange -> assertFalse(exchange.getRates().isEmpty()));
	}
}