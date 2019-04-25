package ir.izo.exchangerate;

import android.content.Context;
import android.content.SharedPreferences;

public class TestUtility {
	public static void clearSharedPrefs(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(context.getClass().getSimpleName(), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.clear();
		editor.commit();
	}
}
