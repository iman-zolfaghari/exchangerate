package ir.izo.exchangerate.controller;

import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.domain.Currency;
import ir.izo.exchangerate.model.CurrencyValueModel;
import ir.izo.exchangerate.restclient.BitcoinAverageRestClient;
import ir.izo.exchangerate.view.CurrencyValueFragmentView;
import org.json.JSONException;
import org.json.JSONObject;

import static ir.izo.exchangerate.util.AndroidUtil.BUNDLE_DATA;
import static ir.izo.exchangerate.util.AndroidUtil.goBack;
import static ir.izo.exchangerate.util.AndroidUtil.handleException;
import static ir.izo.exchangerate.util.Validator.requireNonNull;

/**
 * This class manages the currency value view.
 */
public class CurrencyValueController extends BaseController<CurrencyValueFragmentView, CurrencyValueModel> {

	private Currency currency;
	private BitcoinAverageRestClient bitcoinAverageRestClient;

	public void init() {
		bitcoinAverageRestClient = new BitcoinAverageRestClient();
		currency = (Currency) view.getArguments().getSerializable(BUNDLE_DATA);
		requireNonNull(currency, view, R.string.error_empty_selected_currency);
		model.getCurrencyValue().setText(String.format(view.getString(R.string.message_currency_value), "?", 0.0));

		bitcoinAverageRestClient.loadBitcoinPrice(currency.getSymbol(), this::onStartLoadCurrencyValue,
				this::onFinishLoadCurrencyValue, this::onSuccessLoadCurrencyValue, this::onFailureLoadCurrencyValue);
	}

	private void onFailureLoadCurrencyValue(Throwable throwable) {
		handleException(view.getActivity(), throwable, view.getString(R.string.error_connection_problem));
	}

	private void onSuccessLoadCurrencyValue(JSONObject response) {
		try {
			double price = response.getDouble("price");
			model.getCurrencyValue().setText(String.format(view.getString(R.string.message_currency_value), currency.getSymbol(), price));
		} catch (JSONException e) {
			handleException(view.getActivity(), e, view.getString(R.string.error_internal_problem));
		}
	}

	private void onFinishLoadCurrencyValue() {
		model.getProgressBar().setVisibility(View.INVISIBLE);
	}

	private void onStartLoadCurrencyValue() {
		model.getProgressBar().setVisibility(View.VISIBLE);
	}

	public void back(View v) {
		goBack(view);
	}
}
