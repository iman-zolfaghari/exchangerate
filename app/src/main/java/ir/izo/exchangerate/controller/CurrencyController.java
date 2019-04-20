package ir.izo.exchangerate.controller;

import ir.izo.exchangerate.R;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.model.CurrencyModel;
import ir.izo.exchangerate.view.CurrencyFragmentView;

/**
 * This class manages the currency view.
 */
public class CurrencyController {
	private final static String TAG = CurrencyController.class.getName();

	private CurrencyFragmentView view;
	private CurrencyModel model;

	public CurrencyController(CurrencyFragmentView view, CurrencyModel model) {
		this.view = view;
		this.model = model;
		init();
	}

	private void init() {
		showName();
	}

	private void showName() {
		String name = ApplicationConfig.get(ConfigEnum.NAME);
		model.getName().setText(view.getString(R.string.message_hello, name));
	}
}
