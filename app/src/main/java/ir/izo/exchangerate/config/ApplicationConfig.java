package ir.izo.exchangerate.config;

import android.content.Context;
import android.content.SharedPreferences;
import ir.izo.exchangerate.enums.ConfigEnum;

/**
 * This class is responsible to store and retrieve params from preference file.
 */
public class ApplicationConfig {
	private static SharedPreferences sharedPreferences;

	public static void init(Context context) {
		sharedPreferences = context.getSharedPreferences(context.getClass().getSimpleName(), Context.MODE_PRIVATE);
	}

	public static synchronized void store(ConfigEnum config, String value) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(config.getKey(), value);
		editor.apply();
	}

	public static String get(ConfigEnum config) {
		return sharedPreferences.getString(config.getKey(), config.getDefaultValue());
	}
}
