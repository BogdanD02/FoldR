/**
 * 
 */
package graphics.window.screens;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import graphics.ui.Tabel;
import graphics.ui.buttons.AddFButton;
import graphics.ui.buttons.EditButton;
import graphics.ui.buttons.ExitButton;
import graphics.ui.buttons.InsertButton;
import graphics.ui.buttons.ListButton;
import graphics.ui.buttons.SettingsButton;
import graphics.window.WindowScreen;

/**
 * This class represents the Main (Default) screen shown on
 * application startup.
 * 
 * @since 1.0.0
 * @author David Bogdan
 * @version 1.1.0
 */
public class MainScreen extends WindowScreen {
	private Tabel tb;
	
	/**
	 * Generates the documents table.
	 * 
	 * @return	The generated table
	 */
	private void generateTable() {
		tb.addElement("CUI", true);
		tb.addElement("Denumire", true);
		tb.addElement("Locatie", true);
		tb.addElement("Cod CASS", true);
		
		super.getConstraints().anchor = GridBagConstraints.NORTH;
		super.getConstraints().gridx = GridBagConstraints.RELATIVE;
		super.getConstraints().gridy = 0;
		super.getConstraints().insets = new Insets(20,20,20,20);
		super.getConstraints().gridwidth = 4;
		super.getConstraints().gridheight = 10;
	}
	
	/**
	 * Adds elements to the table
	 * 
	 * @param	elem	The text being added
	 */
	public void addElement(String elem) {
		tb.addElement(elem, false);
	}
	
	/**
	 * Empties the table content
	 */
	public void refreshTable() {
		tb.empty();
		
		tb.addElement("CUI", true);
		tb.addElement("Denumire", true);
		tb.addElement("Locatie", true);
		tb.addElement("Cod CASS", true);
	}
	
	/**
	 * Constructs the main screen.
	 * 
	 * @param	listener	An action listener to pass to buttons.
	 */
	public MainScreen(ActionListener listener) {
		super();
		
		InsertButton btn1 = new InsertButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 0;
		super.getConstraints().weighty = 1;
		super.getConstraints().anchor = GridBagConstraints.NORTHWEST;
		super.getConstraints().insets = new Insets(20,20,20,20);
		super.addComponent(btn1, listener);
		
		EditButton btn6 = new EditButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = GridBagConstraints.RELATIVE;
		super.getConstraints().insets = margins;
		super.addComponent(btn6, listener);
		
		ListButton btn4 = new ListButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = GridBagConstraints.RELATIVE;
		super.getConstraints().insets = margins;
		super.addComponent(btn4, listener);
		
		AddFButton btn3 = new AddFButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = GridBagConstraints.RELATIVE;
		super.getConstraints().insets = margins;
		super.addComponent(btn3, listener);
		
		SettingsButton btn5 = new SettingsButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = GridBagConstraints.RELATIVE;
		super.getConstraints().insets = margins;
		super.addComponent(btn5, listener);
		
		ExitButton btn7 = new ExitButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = GridBagConstraints.RELATIVE;
		super.getConstraints().insets = margins;
		super.addComponent(btn7, listener);
		
		tb = new Tabel(4);
		generateTable();
		super.addComponent(tb);
	}
}
