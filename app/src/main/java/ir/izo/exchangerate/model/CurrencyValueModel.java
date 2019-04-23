package ir.izo.exchangerate.model;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CurrencyValueModel {

	private TextView currencyValue;
	private Button backButton;
	private ProgressBar progressBar;

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

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}
}
