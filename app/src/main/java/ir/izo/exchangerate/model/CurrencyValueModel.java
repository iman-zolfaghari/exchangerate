package ir.izo.exchangerate.model;

import android.widget.Button;
import android.widget.TextView;

public class CurrencyValueModel {

	private TextView currencyValue;
	private Button backButton;

	public TextView getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(TextView currencyValue) {
		this.currencyValue = currencyValue;
	}

	public Button getBackButton() {
		return backButton;
	}

	public void setBackButton(Button backButton) {
		this.backButton = backButton;
	}
}
