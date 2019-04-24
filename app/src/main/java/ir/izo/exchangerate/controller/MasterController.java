package ir.izo.exchangerate.controller;

import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.view.MasterActivityView;

import static android.text.TextUtils.isEmpty;
import static ir.izo.exchangerate.util.AndroidUtil.goToFragmentWithoutBackStack;

public class MasterController {
	private MasterActivityView view;

	public MasterController(MasterActivityView view) {
		this.view = view;
	}

	public void onStart() {
		showFirstFragment();
	}

	public void showFirstFragment() {
		FragmentEnum fragment = isEmpty(ApplicationConfig.get(ConfigEnum.NAME)) ? FragmentEnum.FRAGMENT_INITIALIZER : FragmentEnum.FRAGMENT_CURRENCY;
		goToFragmentWithoutBackStack(view, fragment, null);
	}
}
