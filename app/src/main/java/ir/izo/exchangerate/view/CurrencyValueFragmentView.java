package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.CurrencyValueController;
import ir.izo.exchangerate.model.CurrencyValueModel;

/**
 * The screen that shows currency.
 */
public class CurrencyValueFragmentView extends BaseFragment {
	private CurrencyValueModel model;
	private CurrencyValueController controller;

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_currency_value;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		buildModel(view);

		controller = new CurrencyValueController(this, model);

		model.getBackButton().setOnClickListener(controller::back);
	}

	private void buildModel(View view) {
		model = new CurrencyValueModel();
		model.setCurrencyValue(view.findViewById(R.id.currency_value));
		model.setBackButton(view.findViewById(R.id.back_button));
		model.setProgressBar(view.findViewById(R.id.progress_bar));
	}

}
