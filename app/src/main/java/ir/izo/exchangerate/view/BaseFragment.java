package ir.izo.exchangerate.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import ir.izo.exchangerate.controller.Controller;
import ir.izo.exchangerate.model.Model;
import ir.izo.exchangerate.util.Logger;

import static ir.izo.exchangerate.util.AndroidUtil.handleException;

public abstract class BaseFragment<M extends Model, V extends MyView, C extends Controller<V, M>> extends Fragment implements MyView {
	protected final Logger logger = new Logger(getClass().getName());

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

	protected final void addOnClickListener(Button view, View.OnClickListener listener) {
		view.setOnClickListener(v -> {
			try {
				logger.info("Button \"%s\" has clicked!", view.getText());
				listener.onClick(v);
			} catch (Exception e) {
				handleException(getActivity(), e, null);
			}
		});
	}

	protected final void addOnClickListener(AutoCompleteTextView view, AdapterView.OnItemClickListener listener) {
		view.setOnItemClickListener((parent, view1, position, id) -> {
			try {
				listener.onItemClick(parent, view1, position, id);
			} catch (Exception e) {
				handleException(getActivity(), e, null);
			}
		});

	}
}
