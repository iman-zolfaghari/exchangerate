package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.InitializerController;
import ir.izo.exchangerate.model.InitializerModel;

/**
 * The home screen that gets name for next usage.
 */
public class InitializerFragmentView extends BaseFragment {
	private InitializerModel model;
	private InitializerController controller;

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_initializer;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		buildModel(view);

		controller = new InitializerController(this, model);

		model.getAddNameButton().setOnClickListener(controller::addName);
	}

	private void buildModel(View view) {
		model = new InitializerModel();
		model.setAddNameButton(view.findViewById(R.id.add_name_button));
		model.setName(view.findViewById(R.id.name));
	}

}
