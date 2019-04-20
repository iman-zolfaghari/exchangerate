package ir.izo.exchangerate.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {

	protected abstract int getFragmentLayoutId();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(getFragmentLayoutId(), parent, false);
	}

	protected void handleException(Exception e){
		Toast.makeText(this.getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
	}

}
