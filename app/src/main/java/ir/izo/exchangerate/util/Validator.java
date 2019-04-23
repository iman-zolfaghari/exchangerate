package ir.izo.exchangerate.util;

import android.app.Fragment;
import android.text.TextUtils;

public class Validator {

	public static void requireNonEmpty(String value, Fragment view, int errorMessagePatternId, Object... params) {
		if (TextUtils.isEmpty(value)) {
			throw new IllegalArgumentException(view.getString(errorMessagePatternId, params));
		}
	}

	public static void requireNonNull(Object value, Fragment view, int errorMessagePatternId, Object... params) {
		if (value == null) {
			throw new IllegalArgumentException(view.getString(errorMessagePatternId, params));
		}
	}

}
