package graphics.ui.buttons;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

public final class ContinueButton extends Button {
	private int screenCode;
	
	public ContinueButton(int screenCode) {
		super();
		
		this.Model = new JButton("Mai departe");
		this.screenCode = screenCode;
		
		this.Model.setPreferredSize(new Dimension(200, 30));
		this.Model.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public int onClick() {
		return 11 + screenCode;
	}

	@Override
	public void onFocus() {
		// TODO Auto-generated method stub

	}

}
