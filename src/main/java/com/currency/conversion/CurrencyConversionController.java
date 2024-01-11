package com.currency.conversion;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {
	

	@Autowired
	private CurrencyExchangeProxy currencyProxy;

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConverion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyConversion currencyConversion = new CurrencyConversion();
		HashMap<String, String> uriVariable = new HashMap<>();
		uriVariable.put("from", from);
		CurrencyConversion currecyConversion = currencyProxy.retrieveValue(from, to);
		return new CurrencyConversion(currecyConversion.getId(), from, to, quantity.multiply(quantity),
				currencyConversion.getConversionMultiple(), BigDecimal.ONE, currecyConversion.getEnvironment());

	}

	
	
}
