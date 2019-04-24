package ir.izo.exchangerate.controller;

import android.view.View;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.model.InitializerModel;
import ir.izo.exchangerate.util.Logger;
import ir.izo.exchangerate.view.InitializerFragmentView;

import static ir.izo.exchangerate.util.AndroidUtil.goToFragmentWithoutBackStack;
import static ir.izo.exchangerate.util.Validator.requireNonEmpty;

/**
 * This class manages the home view.
 */
public class InitializerController extends BaseController<InitializerFragmentView, InitializerModel>{
	private final static Logger logger = new Logger(InitializerController.class.getName());


	public InitializerController(InitializerFragmentView view, InitializerModel model) {
		super(view, model);
	}

	public void addName(View v) {
		String name = model.getName().getText().toString();
		logger.info("The name is %s.", name);

		requireNonEmpty(name, view, R.string.error_invalid_name);

		ApplicationConfig.store(ConfigEnum.NAME, name);
		goToFragmentWithoutBackStack(view.getActivity(), FragmentEnum.FRAGMENT_CURRENCY, null);
	}
}
