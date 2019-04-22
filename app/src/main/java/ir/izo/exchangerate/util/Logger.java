package ir.izo.exchangerate.util;

import android.util.Log;

/**
 * This class is a wrapper of the android logger class.
 */
public class Logger {
	private String tag;

	public Logger(String tag) {
		this.tag = tag;
	}

	public void info(String pattern, Object... args) {
		Log.i(tag, String.format(pattern, args));
	}

	public void error(Throwable tr, String pattern, Object... args) {
		Log.e(tag, String.format(pattern, args), tr);
	}
}
