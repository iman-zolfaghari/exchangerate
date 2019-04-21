package ir.izo.exchangerate.controller;

import android.widget.ArrayAdapter;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.model.CurrencyModel;
import ir.izo.exchangerate.view.CurrencyFragmentView;

import java.util.ArrayList;
import java.util.List;

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

		List<String> currencies = new ArrayList<>();
		currencies.add("bitcon");
		currencies.add("dollar");
		currencies.add("Rial");
		currencies.add("Rupie");
		currencies.add("Dinar");
		addEmailsToAutoComplete(currencies);
	}

	/**
	 * Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
	 */
	private void addEmailsToAutoComplete(List<String> emails) {
		ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getActivity(), android.R.layout.simple_dropdown_item_1line, emails);
		model.getCurrency().setAdapter(adapter);
	}


	private void showName() {
		String name = ApplicationConfig.get(ConfigEnum.NAME);
		model.getName().setText(view.getString(R.string.message_hello, name));
	}
}
