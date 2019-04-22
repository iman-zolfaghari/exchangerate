package ir.izo.exchangerate.util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.view.BaseFragment;

public class AndroidUtil {

	private final static Logger logger = new Logger(AndroidUtil.class.getName());

	public static void goToFragment(Activity parentActivity, FragmentEnum fragment) {
		goToFragment(parentActivity, fragment.getFragment(), R.id.fragment_place_holder, true);
	}

	public static void goToFragmentWithoutBackStack(Activity parentActivity, FragmentEnum fragment) {
		goToFragment(parentActivity, fragment.getFragment(), R.id.fragment_place_holder, false);
	}

	public static void goToFragment(Activity parentActivity, BaseFragment fragment, int fragmentPlaceHolderResourceId, boolean addToBackStack) {
		FragmentManager fragmentManager = parentActivity.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(fragmentPlaceHolderResourceId, fragment);
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (addToBackStack) {
			//TODO : this is wrong! I should use previous fragment here.
			fragmentTransaction.addToBackStack(fragment.getTag());
		}
		fragmentTransaction.commit();
	}

	public static void handleException(Activity activity, Throwable tr, String message) {
		String msg = message == null ? tr.getMessage() : message;
		logger.error(tr, msg);
		Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
	}

}
