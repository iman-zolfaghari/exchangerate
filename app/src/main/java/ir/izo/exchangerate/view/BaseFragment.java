package ir.izo.exchangerate.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ir.izo.exchangerate.controller.Controller;
import ir.izo.exchangerate.model.Model;

import static ir.izo.exchangerate.util.AndroidUtil.handleException;

public abstract class BaseFragment<M extends Model, V extends MyView, C extends Controller<V, M>> extends Fragment implements MyView {

	protected abstract int getFragmentLayoutId();

	protected abstract M buildModel(View view);

	protected abstract C createController();

	protected void addListeners(M model, C controller) {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(getFragmentLayoutId(), parent, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		M model = buildModel(view);
		C controller = createController();
		controller.setModel(model);
		controller.setView((V) this);
		addListeners(model, controller);
		controller.init();
	}

	protected final void addOnClickListener(View view, View.OnClickListener listener) {
		view.setOnClickListener(v -> {
			try {
				listener.onClick(v);
			} catch (Exception e) {
				handleException(getActivity(), e, null);
			}
		});
	}

}
