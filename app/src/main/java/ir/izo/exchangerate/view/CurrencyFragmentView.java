package ir.izo.exchangerate.view;

import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.CurrencyController;
import ir.izo.exchangerate.model.CurrencyModel;

/**
 * The home screen that gets currency for next usage.
 */
public class CurrencyFragmentView extends BaseFragment<CurrencyModel, CurrencyFragmentView, CurrencyController> {

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_currency;
	}

	@Override
	protected CurrencyModel buildModel(View view) {
		CurrencyModel model = new CurrencyModel();
		model.setName(view.findViewById(R.id.name));
		model.setCurrency(view.findViewById(R.id.currency));
		model.setConvertButton(view.findViewById(R.id.convert_button));
		return model;
	}

	@Override
	protected CurrencyController createController() {
		return new CurrencyController();
	}

	@Override
	protected void addListeners(CurrencyModel model, CurrencyController controller) {
		addOnClickListener(model.getConvertButton(), controller::convert);
	}
}
