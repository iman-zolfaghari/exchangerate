package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.CurrencyController;
import ir.izo.exchangerate.model.CurrencyModel;

import static ir.izo.exchangerate.util.AndroidUtil.handleException;

/**
 * The home screen that gets currency for next usage.
 */
public class CurrencyFragmentView extends BaseFragment {
	private CurrencyModel currencyModel;
	private CurrencyController currencyController;

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_currency;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		buildModel(view);

		buildController();

		setActionListeners();
	}

	private void buildModel(View view) {
		currencyModel = new CurrencyModel();
		currencyModel.setName((TextView) view.findViewById(R.id.name));
		currencyModel.setCurrency((AutoCompleteTextView) view.findViewById(R.id.currency));
		currencyModel.setConvertButton((Button) view.findViewById(R.id.convert_button));
	}

	private void buildController() {
		currencyController = new CurrencyController(this, currencyModel);
	}

	private void setActionListeners() {
		currencyModel.getConvertButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					currencyController.convert();
				} catch (Exception e) {
					handleException(getActivity(), e, null);
				}
			}
		});
	}
}
