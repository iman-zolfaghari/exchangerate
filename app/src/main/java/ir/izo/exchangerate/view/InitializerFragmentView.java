package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.InitializerController;
import ir.izo.exchangerate.model.InitializerModel;

/**
 * The home screen that gets name for next usage.
 */
public class InitializerFragmentView extends BaseFragment {
	private InitializerModel initializerModel;
	private InitializerController initializerController;

	@Override
	protected int getFragmentLayoutId() {
		return R.layout.fragment_initializer;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		buildModel(view);

		buildController();

		setActionListeners();
	}

	private void buildModel(View view) {
		initializerModel = new InitializerModel();
		initializerModel.setAddNameButton((Button) view.findViewById(R.id.add_name_button));
		initializerModel.setName((EditText) view.findViewById(R.id.name));
	}

	private void buildController() {
		initializerController = new InitializerController(this, initializerModel);
	}

	private void setActionListeners() {
		initializerModel.getAddNameButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					initializerController.addName();
				} catch (Exception e) {
					handleException(e);
				}
			}
		});
	}
}
