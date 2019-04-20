package ir.izo.exchangerate;

import android.app.Application;
import ir.izo.exchangerate.config.ApplicationConfig;

/**
 * Application startup class.
 */

public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		initApplicationConfig();
	}

	/**
	 * This method initializes the shared preference file.
	 */
	private void initApplicationConfig() {
		ApplicationConfig.init(this);
	}
}
