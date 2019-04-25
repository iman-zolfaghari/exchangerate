package ir.izo.exchangerate.controller;

import ir.izo.exchangerate.model.Model;
import ir.izo.exchangerate.util.Logger;
import ir.izo.exchangerate.view.MyView;

public abstract class BaseController<V extends MyView, M extends Model> implements Controller<V, M> {
	protected Logger logger = new Logger(getClass().getName());

	protected V view;
	protected M model;

	@Override
	public void init() {
		logger.info("Controller is initializing...");
	}

	@Override
	public void setView(V view) {
		this.view = view;
	}

	@Override
	public void setModel(M model) {
		this.model = model;
	}
}
