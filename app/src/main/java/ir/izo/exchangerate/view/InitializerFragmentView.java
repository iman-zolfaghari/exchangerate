package ir.izo.exchangerate.view;

import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.InitializerController;
import ir.izo.exchangerate.model.InitializerModel;

/**
 * The home screen that gets name for next usage.
 */
public class InitializerFragmentView extends BaseFragment<InitializerModel, InitializerFragmentView, InitializerController> {

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_initializer;
	}

	@Override
	protected InitializerModel buildModel(View view) {
		InitializerModel model = new InitializerModel();
		model.setAddNameButton(view.findViewById(R.id.add_name_button));
		model.setName(view.findViewById(R.id.name));
		return model;
	}

	@Override
	protected InitializerController createController() {
		return new InitializerController();
	}

	@Override
	protected void addListeners(InitializerModel model, InitializerController controller) {
		addOnClickListener(model.getAddNameButton(), controller::addName);
	}
}
