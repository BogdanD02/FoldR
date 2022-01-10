package graphics.ui.buttons;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

/**
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.2.0
 */
public final class SaveButton extends Button {

	/**
	 * Instantiates a new button to lead to the previous section
	 */
	public SaveButton() {
		super();
		
		this.Model = new JButton("Adăugare");
		
		this.Model.setPreferredSize(new Dimension(200, 30));
		this.Model.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public int onClick(){
		return 25;
	}

	@Override
	public void onFocus() {
		// TODO Auto-generated method stub

	}

}
