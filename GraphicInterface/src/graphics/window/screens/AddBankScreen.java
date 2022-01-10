package graphics.window.screens;

import java.awt.Insets;
import java.awt.event.ActionListener;

import functionals.containers.Bank;
import graphics.ui.TextBox;
import graphics.ui.buttons.BackButton;
import graphics.ui.buttons.SaveBankBtn;
import graphics.window.WindowScreen;

/**
 * This class models an extension of the input menu screen.
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class AddBankScreen extends WindowScreen {
	private TextBox nume, filiala, cont, moneda;
	
	/**
	 * Instantiates a new bank input screen.
	 * 
	 * @param	listener	An action listener to be passed down to buttons.
	 */
	public AddBankScreen(ActionListener listener) {
		nume = new TextBox("Nume: ", "");
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 0;
		super.getConstraints().gridwidth = 1;
		super.getConstraints().gridheight = 1;
		super.getConstraints().insets = new Insets(20,20,20,20);
		super.addComponent(nume);
		
		filiala = new TextBox("Filiala: ", "");
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 0;
		super.getConstraints().insets = new Insets(20,40,20,20);
		super.addComponent(filiala);
	
		cont = new TextBox("Cont: ", "");
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 1;
		super.getConstraints().insets = new Insets(0,30,20,20);
		super.addComponent(cont);
		
		moneda = new TextBox("Moneda: ", "");
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 1;
		super.getConstraints().insets = margins;
		super.addComponent(moneda);
		
		BackButton backBtn = new BackButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 2;
		super.getConstraints().insets = margins;
		super.addComponent(backBtn, listener);
		
		SaveBankBtn saveBtn = new SaveBankBtn();
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 2;
		super.getConstraints().insets = margins;
		super.addComponent(saveBtn, listener);
	}

	/**
	 * Collects the data from text boxes.
	 * 
	 * This function collects the data from text boxes and
	 * stores it in a Bank-type container, later returning it.
	 * 
	 * @return	A Bank-type container.
	 */
	public Bank getData() {
		Bank b = new Bank();
		
		b.Acc = cont.getText();
		b.Coin = moneda.getText();
		b.Name = nume.getText();
		b.Loc = filiala.getText();
		
		return b;
	}
	
	/**
	 * Clears the content of the text fields.
	 */
	public void reset() {
		nume.reset();
		filiala.reset();
		cont.reset();
		moneda.reset();
	}
}
