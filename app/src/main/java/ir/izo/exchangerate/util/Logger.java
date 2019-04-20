package ir.izo.exchangerate.util;

import android.util.Log;

/**
 * This class is a wrapper of the android logger class.
 */
public class Logger {
	public static void info(String tag, String pattern, Object... args) {
		Log.i(tag, String.format(pattern, args));
	}
}
