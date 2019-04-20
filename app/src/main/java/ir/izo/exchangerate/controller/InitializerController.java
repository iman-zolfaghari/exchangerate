package ir.izo.exchangerate.controller;

import android.text.TextUtils;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.exception.InvalidParamException;
import ir.izo.exchangerate.model.InitializerModel;
import ir.izo.exchangerate.util.AndroidUtil;
import ir.izo.exchangerate.util.Logger;
import ir.izo.exchangerate.view.InitializerFragmentView;

/**
 * This class manages the home view.
 */
public class InitializerController {
	private final static String TAG = InitializerController.class.getName();

	private InitializerFragmentView initializerFragmentView;
	private InitializerModel initializerModel;

	public InitializerController(InitializerFragmentView initializerFragmentView, InitializerModel initializerModel) {
		this.initializerFragmentView = initializerFragmentView;
		this.initializerModel = initializerModel;
	}

	public void addName() {
		String name = initializerModel.getName().getText().toString();
		Logger.info(TAG, "The name is %s.", name);
		if (TextUtils.isEmpty(name)) {
			throw new InvalidParamException("Name is empty!");
		}
		ApplicationConfig.store(ConfigEnum.NAME, name);
		AndroidUtil.goToFragment(FragmentEnum.CURRENCY_INITIALIZER, initializerFragmentView.getActivity());
	}
}
