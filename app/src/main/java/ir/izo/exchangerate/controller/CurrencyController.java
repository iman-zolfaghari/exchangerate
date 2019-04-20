package ir.izo.exchangerate.controller;

import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.model.CurrencyModel;
import ir.izo.exchangerate.view.CurrencyFragmentView;

/**
 * This class manages the currency view.
 */
public class CurrencyController {
	private final static String TAG = CurrencyController.class.getName();

	private CurrencyFragmentView currencyFragmentView;
	private CurrencyModel currencyModel;

	public CurrencyController(CurrencyFragmentView currencyFragmentView, CurrencyModel currencyModel) {
		this.currencyFragmentView = currencyFragmentView;
		this.currencyModel = currencyModel;
		init();
	}

	private void init() {
		showName();
	}

	private void showName() {
		String name = ApplicationConfig.get(ConfigEnum.NAME);
		currencyModel.getName().setText(String.format("Hi %s", name));
	}
}
