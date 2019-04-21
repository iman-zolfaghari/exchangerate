package ir.izo.exchangerate.util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.view.BaseFragment;

public class AndroidUtil {

	public static void goToFragment(FragmentEnum fragmentEnum, Activity activity) {
		goToFragment(R.id.fragment_place_holder, fragmentEnum.getFragment(), activity, true);
	}

	public static void goToFragmentWithoutBackStack(FragmentEnum fragmentEnum, Activity activity) {
		goToFragment(R.id.fragment_place_holder, fragmentEnum.getFragment(), activity, false);
	}

	public static void goToFragment(int fragmentPlaceHolderResourceId, BaseFragment fragment, Activity activity, boolean addToBackStack) {
		FragmentManager fragmentManager = activity.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(fragmentPlaceHolderResourceId, fragment);
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (addToBackStack) {
			fragmentTransaction.addToBackStack(fragment.getTag());
		}
		fragmentTransaction.commit();
	}

}
