package ir.izo.exchangerate.controller;

import ir.izo.exchangerate.model.Model;
import ir.izo.exchangerate.view.MyView;

public interface Controller<V extends MyView, M extends Model> {
	void init();

	void setView(V view);

	void setModel(M model);
}
