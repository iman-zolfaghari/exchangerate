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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
		model.getCurrency().setText("");
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
			currencies = convertToRateList(response);
			fillRateAutoCompleteAdapter();
		} catch (Exception e) {
			currencies = null;
			handleException(view.getActivity(), e, view.getString(R.string.error_internal_problem));
		}
	}

	private void onFailureLoadCurrencies(Throwable throwable) {
		handleException(view.getActivity(), throwable, view.getString(R.string.error_connection_problem));
	}

	private List<Currency> convertToRateList(JSONObject response) throws JSONException {
		JSONObject ratesObject = response.getJSONObject("rates");
		Iterator<String> rateNames = ratesObject.keys();
		List<Currency> currencies = new LinkedList<>();
		while (rateNames.hasNext()) {
			String symbol = rateNames.next();
			JSONObject rateObject = (JSONObject) ratesObject.get(symbol);
			Currency currency = new Currency(symbol, rateObject.getDouble("rate"), rateObject.getString("name"));
			currencies.add(currency);
		}
		return currencies;
	}

	private void fillRateAutoCompleteAdapter() {
		model.getConvertButton().setEnabled(true);
		logger.info("Rate list size is : %s", currencies.size());
		adapter = new ArrayAdapter<>(view.getActivity(), android.R.layout.simple_dropdown_item_1line, currencies);
		model.getCurrency().setAdapter(adapter);
		model.getCurrency().setOnItemClickListener(this::selectRate);
	}

	private void selectRate(AdapterView<?> parent, View view, int position, long id) {
		selectedCurrency = adapter.getItem(position);
		logger.info("onItemClick id is %s and position is %s and symbol is %s !!!", id, position, selectedCurrency == null ? null : selectedCurrency.getSymbol());
	}

	public void convert(View v) {
		requireNonNull(selectedCurrency, view, R.string.error_empty_selected_currency);
		goToFragment(view, FragmentEnum.FRAGMENT_CURRENCY_VALUE, selectedCurrency);
	}
}
