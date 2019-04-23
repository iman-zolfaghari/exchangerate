package ir.izo.exchangerate.controller;

import ir.izo.exchangerate.R;
import ir.izo.exchangerate.domain.Rate;
import ir.izo.exchangerate.model.CurrencyValueModel;
import ir.izo.exchangerate.util.Logger;
import ir.izo.exchangerate.view.CurrencyValueFragmentView;

import static ir.izo.exchangerate.enums.GlobalVariables.BUNDLE_DATA;
import static ir.izo.exchangerate.util.AndroidUtil.goBack;
import static ir.izo.exchangerate.util.Validator.requireNonNull;

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
		Rate rate = (Rate) view.getArguments().getSerializable(BUNDLE_DATA);
		requireNonNull(rate, view, R.string.error_empty_selected_rate);
		model.getCurrencyValue().setText(String.format(view.getString(R.string.message_currency_value), rate.getSymbol(), rate.getRate()));
	}

	public void back() {
		goBack(view);
	}
}
