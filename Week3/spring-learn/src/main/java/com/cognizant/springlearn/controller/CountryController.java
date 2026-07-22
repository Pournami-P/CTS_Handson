package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;

@RestController
public class CountryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	private final CountryService countryService;

	public CountryController(CountryService countryService) {
		LOGGER.debug("Inside CountryController Constructor");
		this.countryService = countryService;
	}

	/**
	 * Hands on: REST - Country Web Service.
	 * GET http://localhost:8090/country -> India country details.
	 */
	@RequestMapping("/country")
	public Country getCountryIndia() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country country = context.getBean("country", Country.class);
		LOGGER.debug("Country : {}", country.toString());
		LOGGER.info("END");
		return country;
	}

	/**
	 * Hands on: REST - Get country based on country code (case insensitive).
	 * GET http://localhost:8090/countries/{code} -> matching country details.
	 */
	@GetMapping("/countries/{code}")
	public Country getCountry(@PathVariable String code) {
		LOGGER.info("START");
		Country country = countryService.getCountry(code);
		LOGGER.info("END");
		return country;
	}
}
