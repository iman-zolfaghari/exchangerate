package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.CurrencyController;
import ir.izo.exchangerate.model.CurrencyModel;

/**
 * The home screen that gets currency for next usage.
 */
public class CurrencyFragmentView extends BaseFragment {
	private CurrencyModel model;
	private CurrencyController controller;

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_currency;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		buildModel(view);

		controller = new CurrencyController(this, model);

		model.getConvertButton().setOnClickListener(controller::convert);
	}

	private void buildModel(View view) {
		model = new CurrencyModel();
		model.setName(view.findViewById(R.id.name));
		model.setCurrency(view.findViewById(R.id.currency));
		model.setConvertButton(view.findViewById(R.id.convert_button));
	}

}
