package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.MasterController;

public class MasterActivityView extends AppCompatActivity {

	private MasterController masterController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_master);

		buildController();
	}

	private void buildController() {
		masterController = new MasterController(this);
	}

}
