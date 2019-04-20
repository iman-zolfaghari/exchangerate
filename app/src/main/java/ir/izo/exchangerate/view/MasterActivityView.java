package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.enums.FragmentEnum;
import ir.izo.exchangerate.util.AndroidUtil;

public class MasterActivityView extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_master);

		AndroidUtil.goToFragment(FragmentEnum.FRAGMENT_INITIALIZER, this);
	}

}
