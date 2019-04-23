package ir.izo.exchangerate.controller;

import android.text.TextUtils;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.util.AndroidUtil;
import ir.izo.exchangerate.view.MasterActivityView;

public class MasterController {
	private MasterActivityView view;

	public MasterController(MasterActivityView view) {
		this.view = view;
	}

	public void onStart() {
		showFirstFragment();
	}

	public void showFirstFragment() {
		FragmentEnum fragment = TextUtils.isEmpty(ApplicationConfig.get(ConfigEnum.NAME)) ? FragmentEnum.FRAGMENT_INITIALIZER : FragmentEnum.FRAGMENT_CURRENCY;
		AndroidUtil.goToFragmentWithoutBackStack(view, fragment, null);
	}
}
