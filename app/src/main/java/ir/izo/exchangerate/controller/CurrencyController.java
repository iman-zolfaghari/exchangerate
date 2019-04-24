package ir.izo.exchangerate.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.domain.Rate;
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
public class CurrencyController  extends BaseController<CurrencyFragmentView, CurrencyModel> {

	private static List<Rate> rates;

	private Rate selectedRate;
	private ArrayAdapter<Rate> adapter;

	public CurrencyController(CurrencyFragmentView view, CurrencyModel model) {
		super(view, model);
	}

	protected void init() {
		selectedRate = null;
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
		JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				try {
					rates = convertToRateList(response);
					fillRateAutoCompleteAdapter();
				} catch (Exception e) {
					rates = null;
					handleException(view.getActivity(), e, view.getString(R.string.error_internal_problem));
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				handleException(view.getActivity(), throwable, view.getString(R.string.error_connection_problem));
			}
		};
		if (rates == null) {
			BitcoinAverageRestClient.get("/constants/exchangerates/global", null, handler);
		} else {
			fillRateAutoCompleteAdapter();
		}
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

	private void fillRateAutoCompleteAdapter() {
		model.getConvertButton().setEnabled(true);
		logger.info("Rate list size is : %s", rates.size());
		adapter = new ArrayAdapter<>(view.getActivity(), android.R.layout.simple_dropdown_item_1line, rates);
		model.getCurrency().setAdapter(adapter);
		model.getCurrency().setOnItemClickListener(this::selectRate);
	}

	private void selectRate(AdapterView<?> parent, View view, int position, long id) {
		selectedRate = adapter.getItem(position);
		logger.info("onItemClick id is %s and position is %s and symbol is %s !!!", id, position, selectedRate == null ? null : selectedRate.getSymbol());
	}

	public void convert(View v) {
		requireNonNull(selectedRate, view, R.string.error_empty_selected_rate);
		goToFragment(view, FragmentEnum.FRAGMENT_CURRENCY_VALUE, selectedRate);
	}
}
