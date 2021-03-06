package ir.izo.exchangerate.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.view.BaseFragment;

import java.io.Serializable;

/**
 * AndroidUtil contains some utility methods(like working with fragments, activities, ...) for only android.
 */
public class AndroidUtil {

	private final static Logger logger = new Logger(AndroidUtil.class.getName());
	public static final String BUNDLE_DATA = "bundle-data";

	public static void goToFragment(BaseFragment currentFragment, FragmentEnum fragment, Serializable data) {
		goToFragment(currentFragment.getActivity(), currentFragment, fragment.getFragment(), data, R.id.fragment_place_holder, true);
	}

	public static void goToFragment(Activity parentActivity, BaseFragment currentFragment, BaseFragment fragment, Serializable data, int fragmentPlaceHolderResourceId, boolean addToBackStack) {
		Bundle args = new Bundle();
		args.putSerializable(BUNDLE_DATA, data);
		fragment.setArguments(args);

		FragmentManager fragmentManager = parentActivity.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(fragmentPlaceHolderResourceId, fragment);
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (addToBackStack) {
			fragmentTransaction.addToBackStack(currentFragment.getTag());
		}
		fragmentTransaction.commit();
	}

	public static void goToFragmentWithoutBackStack(Activity parentActivity, FragmentEnum fragment, Serializable data) {
		goToFragment(parentActivity, null, fragment.getFragment(), data, R.id.fragment_place_holder, false);
	}

	public static void goBack(Fragment fragment) {
		fragment.getActivity().getFragmentManager().popBackStack();
	}

	public static void handleException(Activity activity, Throwable tr, String message) {
		String msg = message == null ? tr.getMessage() : message;
		logger.error(tr, msg);
		Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
	}

}
