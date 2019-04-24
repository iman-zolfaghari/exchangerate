package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.MasterController;

public class MasterActivityView extends AppCompatActivity {

	private MasterController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_master);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);

		controller = new MasterController(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		controller.onStart();
	}
}
