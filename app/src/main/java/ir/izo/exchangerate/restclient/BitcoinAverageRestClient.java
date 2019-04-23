package ir.izo.exchangerate.restclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BitcoinAverageRestClient {
//	curl https://apiv2.bitcoinaverage.com/constants/exchangerates/global
//	curl https://apiv2.bitcoinaverage.com/convert/global?from=BTC&to=USD&amount=2

	private static final String BASE_URL = "https://apiv2.bitcoinaverage.com";

	private static AsyncHttpClient client = new AsyncHttpClient();
	static {
		client.setMaxRetriesAndTimeout(3, 5000);
	}

	public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

}