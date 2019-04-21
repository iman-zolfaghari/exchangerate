package ir.izo.exchangerate.model;

import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class CurrencyModel {

	private TextView name;
	private AutoCompleteTextView currency;

	public TextView getName() {
		return name;
	}

	public void setName(TextView name) {
		this.name = name;
	}

	public AutoCompleteTextView getCurrency() {
		return currency;
	}

	public void setCurrency(AutoCompleteTextView currency) {
		this.currency = currency;
	}
}
