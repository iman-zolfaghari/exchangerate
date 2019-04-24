package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.InitializerController;
import ir.izo.exchangerate.model.InitializerModel;

/**
 * The home screen that gets name for next usage.
 */
public class InitializerFragmentView extends BaseFragment implements MyView{
	private InitializerController controller;

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_initializer;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		InitializerModel model = buildModel(view);

		controller = new InitializerController(this, model);

		addOnClickListener(model.getAddNameButton(), controller::addName);
	}

	private InitializerModel buildModel(View view) {
		InitializerModel model = new InitializerModel();
		model.setAddNameButton(view.findViewById(R.id.add_name_button));
		model.setName(view.findViewById(R.id.name));
		return model;
	}

}
