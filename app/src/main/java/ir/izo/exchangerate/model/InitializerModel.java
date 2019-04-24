package ir.izo.exchangerate.model;

import android.widget.Button;
import android.widget.EditText;

public class InitializerModel implements Model{
	private Button addNameButton;
	private EditText name;

	public Button getAddNameButton() {
		return addNameButton;
	}

	public void setAddNameButton(Button addNameButton) {
		this.addNameButton = addNameButton;
	}

	public EditText getName() {
		return name;
	}

	public void setName(EditText name) {
		this.name = name;
	}

}
