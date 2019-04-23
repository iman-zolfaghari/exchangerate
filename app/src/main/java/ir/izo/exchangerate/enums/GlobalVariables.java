package ir.izo.exchangerate.enums;

import ir.izo.exchangerate.domain.Rate;

import java.util.List;

public class GlobalVariables {
	public static final String BUNDLE_DATA = "bundle-data";
	private static List<Rate> rates;

	public static List<Rate> getRates() {
		return rates;
	}

	public static void setRates(List<Rate> rates) {
		GlobalVariables.rates = rates;
	}
}
