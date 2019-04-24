package ir.izo.exchangerate.restclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import ir.izo.exchangerate.domain.Action;
import ir.izo.exchangerate.domain.Consumer;
import ir.izo.exchangerate.domain.Currency;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BitcoinAverageRestClient {
	//	curl https://apiv2.bitcoinaverage.com/constants/exchangerates/global
//	curl https://apiv2.bitcoinaverage.com/convert/global?from=BTC&to=USD&amount=2

	private static final String BASE_URL = "https://apiv2.bitcoinaverage.com";
	public static final String URL_CONVERT_GLOBAL = "/convert/global";
	public static final String URL_CURRENCIES = "/constants/exchangerates/global";

	private static AsyncHttpClient client = new AsyncHttpClient();

	static {
		client.setMaxRetriesAndTimeout(3, 5000);
	}

	public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void get(String url, RequestParams params, Action onStartAction, Action onFinishAction, Consumer<JSONObject> onSuccessConsumer, Consumer<Throwable> onFailureConsumer) {
		client.get(getAbsoluteUrl(url), params, new JsonHttpResponseHandler() {

			@Override
			public void onStart() {
				if (onStartAction != null) {
					onStartAction.doAction();
				}
			}

			@Override
			public void onFinish() {
				if (onFinishAction != null) {
					onFinishAction.doAction();
				}
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				onSuccessConsumer.consume(response);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				onFailureConsumer.consume(throwable);
			}

		});
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

	public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void loadBitcoinPrice(String to, Action onStartAction, Action onFinishAction, Consumer<JSONObject> onSuccessConsumer, Consumer<Throwable> onFailureConsumer) {
		RequestParams params = new RequestParams("from", "BTC", "to", to, "amount", 1);
		BitcoinAverageRestClient.get(URL_CONVERT_GLOBAL, params, onStartAction, onFinishAction, onSuccessConsumer, onFailureConsumer);
	}

	public static void loadCurrencies(Consumer<JSONObject> onSuccessConsumer, Consumer<Throwable> onFailureConsumer) {
		BitcoinAverageRestClient.get(URL_CURRENCIES, null, null, null, onSuccessConsumer, onFailureConsumer);
	}


	public static List<Currency> convertToCurrencyList(JSONObject response) throws JSONException {
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

}