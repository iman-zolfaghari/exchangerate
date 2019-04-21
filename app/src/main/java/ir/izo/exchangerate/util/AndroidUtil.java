package ir.izo.exchangerate.util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.view.BaseFragment;

public class AndroidUtil {

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
			fragmentTransaction.addToBackStack(fragment.getTag());
		}
		fragmentTransaction.commit();
	}

}
