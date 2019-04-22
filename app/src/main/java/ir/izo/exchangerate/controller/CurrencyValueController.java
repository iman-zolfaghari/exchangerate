package ir.izo.exchangerate.controller;

import ir.izo.exchangerate.R;
import ir.izo.exchangerate.model.CurrencyValueModel;
import ir.izo.exchangerate.util.Logger;
import ir.izo.exchangerate.view.CurrencyValueFragmentView;

/**
 * This class manages the currency value view.
 */
public class CurrencyValueController {

	private final static Logger logger = new Logger(CurrencyValueController.class.getName());

	private CurrencyValueFragmentView view;
	private CurrencyValueModel model;

	public CurrencyValueController(CurrencyValueFragmentView view, CurrencyValueModel model) {
		this.view = view;
		this.model = model;
		init();
	}

	private void init() {
		model.getCurrencyValue().setText(view.getString(R.string.message_currency_value, "0"));
	}

	public void back() {
		//TODO back
	}
}
