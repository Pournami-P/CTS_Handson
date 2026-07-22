package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;

/**
 * Hands on: REST - Get country based on country code.
 */
@Service
public class CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

	@SuppressWarnings("unchecked")
	public Country getCountry(String code) {
		LOGGER.info("START");
		LOGGER.debug("code : {}", code);

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countryList = context.getBean("countryList", List.class);

		// Case insensitive match using a lambda expression instead of a manual loop
		Country result = countryList.stream()
				.filter(country -> country.getCode().equalsIgnoreCase(code))
				.findFirst()
				.orElse(null);

		LOGGER.debug("Result : {}", result);
		LOGGER.info("END");
		return result;
	}
}
