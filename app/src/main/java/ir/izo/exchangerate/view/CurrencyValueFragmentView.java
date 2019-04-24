package ir.izo.exchangerate.view;

import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.CurrencyValueController;
import ir.izo.exchangerate.model.CurrencyValueModel;

/**
 * The screen that shows currency.
 */
public class CurrencyValueFragmentView extends BaseFragment<CurrencyValueModel, CurrencyValueFragmentView, CurrencyValueController> {

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_currency_value;
	}

	@Override
	protected CurrencyValueModel buildModel(View view) {
		CurrencyValueModel model = new CurrencyValueModel();
		model.setCurrencyValue(view.findViewById(R.id.currency_value));
		model.setBackButton(view.findViewById(R.id.back_button));
		model.setProgressBar(view.findViewById(R.id.progress_bar));
		return model;
	}

	@Override
	protected CurrencyValueController createController() {
		return new CurrencyValueController();
	}

	@Override
	protected void addListeners(CurrencyValueModel model, CurrencyValueController controller) {
		addOnClickListener(model.getBackButton(), controller::back);
	}
}
