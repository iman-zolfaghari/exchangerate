package ir.izo.exchangerate.controller;

import ir.izo.exchangerate.model.Model;
import ir.izo.exchangerate.util.Logger;
import ir.izo.exchangerate.view.MyView;

public abstract class BaseController<V extends MyView, M extends Model> {
	protected final Logger logger = new Logger(getClass().getName());

	protected V view;
	protected M model;

	public BaseController(V view, M model) {
		this.view = view;
		this.model = model;
		init();
	}

	protected void init() {
	}

}
