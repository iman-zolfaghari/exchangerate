package ir.izo.exchangerate.controller;

import android.widget.ArrayAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.domain.Rate;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.model.CurrencyModel;
import ir.izo.exchangerate.restclient.BitcoinAverageRestClient;
import ir.izo.exchangerate.util.Logger;
import ir.izo.exchangerate.view.CurrencyFragmentView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static ir.izo.exchangerate.util.AndroidUtil.handleException;

/**
 * This class manages the currency view.
 */
public class CurrencyController {

	private final static Logger logger = new Logger(CurrencyController.class.getName());

	private CurrencyFragmentView view;
	private CurrencyModel model;

	public CurrencyController(CurrencyFragmentView view, CurrencyModel model) {
		this.view = view;
		this.model = model;
		init();
	}

	private void init() {
		model.getConvertButton().setEnabled(false);
		showName();
		getCurrencySymbols();
	}

	public void getCurrencySymbols() {
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				try {
					List<Rate> rates = convertToRateList(response);
					fillRateAutoCompleteAdapter(rates);
					model.getConvertButton().setEnabled(true);
				} catch (JSONException e) {
					handleException(view.getActivity(), e, view.getString(R.string.error_internal_problem));
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				handleException(view.getActivity(), throwable, view.getString(R.string.error_connection_problem));
			}
		};
		BitcoinAverageRestClient.get("/constants/exchangerates/global", null, handler);
	}

	private List<Rate> convertToRateList(JSONObject response) throws JSONException {
		JSONObject ratesObject = response.getJSONObject("rates");
		Iterator<String> rateNames = ratesObject.keys();
		List<Rate> rates = new LinkedList<>();
		while (rateNames.hasNext()) {
			String symbol = rateNames.next();
			JSONObject rateObject = (JSONObject) ratesObject.get(symbol);
			Rate rate = new Rate(symbol, rateObject.getDouble("rate"), rateObject.getString("name"));
			rates.add(rate);
		}
		return rates;
	}

	private void fillRateAutoCompleteAdapter(List<Rate> rates) {
		logger.info("Rate list size is : %s", rates.size());
		List<String> items = new LinkedList<>();
		for (Rate rate : rates) {
			items.add(rate.getName() + " - " + rate.getSymbol());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getActivity(), android.R.layout.simple_dropdown_item_1line, items);
		model.getCurrency().setAdapter(adapter);
	}


	private void showName() {
		String name = ApplicationConfig.get(ConfigEnum.NAME);
		model.getName().setText(view.getString(R.string.message_hello, name));
	}
}
