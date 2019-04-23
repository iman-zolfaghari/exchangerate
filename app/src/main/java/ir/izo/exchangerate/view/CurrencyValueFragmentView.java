package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.CurrencyValueController;
import ir.izo.exchangerate.model.CurrencyValueModel;

import static ir.izo.exchangerate.util.AndroidUtil.handleException;

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

		buildController();

		setActionListeners();
	}

	private void buildModel(View view) {
		model = new CurrencyValueModel();
		model.setCurrencyValue((TextView) view.findViewById(R.id.currency_value));
		model.setBackButton((Button) view.findViewById(R.id.back_button));
		model.setProgressBar((ProgressBar) view.findViewById(R.id.progress_bar));
	}

	private void buildController() {
		controller = new CurrencyValueController(this, model);
	}

	private void setActionListeners() {
		model.getBackButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					controller.back();
				} catch (Exception e) {
					handleException(getActivity(), e, null);
				}
			}
		});
	}
}
