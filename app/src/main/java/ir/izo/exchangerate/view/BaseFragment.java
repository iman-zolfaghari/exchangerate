package ir.izo.exchangerate.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(getFragmentLayoutId(), parent, false);
	}

	protected abstract int getFragmentLayoutId();

}
