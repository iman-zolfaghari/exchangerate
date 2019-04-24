package ir.izo.exchangerate.model;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class CurrencyModel implements Model{

	private TextView name;
	private AutoCompleteTextView currency;
	private Button convertButton;

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

	public Button getConvertButton() {
		return convertButton;
	}

	public void setConvertButton(Button convertButton) {
		this.convertButton = convertButton;
	}
}
