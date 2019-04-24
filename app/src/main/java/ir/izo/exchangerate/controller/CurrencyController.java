package ir.izo.exchangerate.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.domain.Currency;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.model.CurrencyModel;
import ir.izo.exchangerate.restclient.BitcoinAverageRestClient;
import ir.izo.exchangerate.view.CurrencyFragmentView;
import org.json.JSONObject;

import java.util.List;

import static ir.izo.exchangerate.restclient.BitcoinAverageRestClient.convertToCurrencyList;
import static ir.izo.exchangerate.util.AndroidUtil.goToFragment;
import static ir.izo.exchangerate.util.AndroidUtil.handleException;
import static ir.izo.exchangerate.util.Validator.requireNonNull;

/**
 * This class manages the currency view.
 */
public class CurrencyController extends BaseController<CurrencyFragmentView, CurrencyModel> {

	private static List<Currency> currencies;

	private Currency selectedCurrency;
	private ArrayAdapter<Currency> adapter;

	public void init() {
		selectedCurrency = null;
		model.getConvertButton().setEnabled(false);
		model.getCurrency().setText(null);
		showName();
		getCurrencySymbols();
	}

	private void showName() {
		String name = ApplicationConfig.get(ConfigEnum.NAME);
		model.getName().setText(view.getString(R.string.message_hello, name));
	}

	public void getCurrencySymbols() {
		if (currencies == null) {
			BitcoinAverageRestClient.loadCurrencies(this::onSuccessLoadCurrencies, this::onFailureLoadCurrencies);
		} else {
			fillRateAutoCompleteAdapter();
		}
	}

	private void onSuccessLoadCurrencies(JSONObject response) {
		try {
			currencies = convertToCurrencyList(response);
			fillRateAutoCompleteAdapter();
		} catch (Exception e) {
			currencies = null;
			handleException(view.getActivity(), e, view.getString(R.string.error_internal_problem));
		}
	}

	private void onFailureLoadCurrencies(Throwable throwable) {
		handleException(view.getActivity(), throwable, view.getString(R.string.error_connection_problem));
	}

	private void fillRateAutoCompleteAdapter() {
		logger.info("Currency list size is : %s", currencies.size());
		model.getConvertButton().setEnabled(true);
		adapter = new ArrayAdapter<>(view.getActivity(), android.R.layout.simple_dropdown_item_1line, currencies);
		model.getCurrency().setAdapter(adapter);
	}

	public void selectCurrency(AdapterView<?> parent, View view, int position, long id) {
		selectedCurrency = adapter.getItem(position);
		logger.info("onItemClick id is %s and position is %s and symbol is %s !!!", id, position, selectedCurrency == null ? null : selectedCurrency.getSymbol());
	}

	public void convert(View v) {
		requireNonNull(selectedCurrency, view, R.string.error_empty_selected_currency);
		model.getCurrency().setText(null);
		goToFragment(view, FragmentEnum.FRAGMENT_CURRENCY_VALUE, selectedCurrency);
	}
}
