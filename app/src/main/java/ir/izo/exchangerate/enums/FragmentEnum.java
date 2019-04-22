package ir.izo.exchangerate.enums;

import ir.izo.exchangerate.view.BaseFragment;
import ir.izo.exchangerate.view.CurrencyFragmentView;
import ir.izo.exchangerate.view.CurrencyValueFragmentView;
import ir.izo.exchangerate.view.InitializerFragmentView;

public enum FragmentEnum {
	FRAGMENT_INITIALIZER(new InitializerFragmentView()),
	FRAGMENT_CURRENCY(new CurrencyFragmentView()),
	FRAGMENT_CURRENCY_VALUE(new CurrencyValueFragmentView()),;

	private BaseFragment fragment;

	FragmentEnum(BaseFragment fragment) {
		this.fragment = fragment;
	}

	public BaseFragment getFragment() {
		return fragment;
	}
}
