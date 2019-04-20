package ir.izo.exchangerate.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ir.izo.exchangerate.R;
import ir.izo.exchangerate.controller.HomeController;
import ir.izo.exchangerate.model.HomeModel;

/**
 * The home screen that gets name for next usage.
 */
public class HomeView extends AppCompatActivity {
	private HomeModel homeModel;
	private HomeController homeController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		buildModel();

		buildController();

		setActionListeners();
	}

	private void buildModel() {
		homeModel = new HomeModel();
		homeModel.setAddNameButton((Button) findViewById(R.id.add_name_button));
		homeModel.setName((EditText) findViewById(R.id.name));
	}

	private void buildController() {
		homeController = new HomeController(this, homeModel);
	}

	private void setActionListeners() {
		homeModel.getAddNameButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				homeController.addName();
			}
		});
	}
}
