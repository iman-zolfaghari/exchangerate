package ir.izo.exchangerate.controller;

import android.widget.Toast;
import ir.izo.exchangerate.config.ApplicationConfig;
import ir.izo.exchangerate.enums.ConfigEnum;
import ir.izo.exchangerate.model.HomeModel;
import ir.izo.exchangerate.util.Logger;
import ir.izo.exchangerate.view.HomeView;

import static android.text.TextUtils.isEmpty;

/**
 * This class manages the home view.
 */
public class HomeController {
	private final static String TAG = HomeController.class.getName();

	private HomeView homeView;
	private HomeModel homeModel;

	public HomeController(HomeView homeView, HomeModel homeModel) {
		this.homeView = homeView;
		this.homeModel = homeModel;
		init();
	}

	private void init() {
		showName();
	}

	public void addName() {
		Logger.info(TAG, "The name is %s.", homeModel.getName().getText().toString());
		ApplicationConfig.store(ConfigEnum.NAME, homeModel.getName().getText().toString());
		showName();
	}

	private void showName() {
		String name = ApplicationConfig.get(ConfigEnum.NAME);
		if (!isEmpty(name)) {
			Toast.makeText(homeView, String.format("Hi %s", name), Toast.LENGTH_LONG).show();
		}
	}
}
