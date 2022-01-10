package graphics.window.screens;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Vector;

import functionals.containers.Bank;
import functionals.containers.Partner;
import functionals.containers.Person;
import graphics.ui.DropDown;
import graphics.ui.Label;
import graphics.ui.Tabel;
import graphics.ui.TextBox;
import graphics.ui.buttons.*;
import graphics.window.WindowScreen;

/**
 * This class models the input menu screen.
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class AlterScreen extends WindowScreen {
	private DropDown list1, list2, list3, list4;
	private TextBox input1, input2, input3, input4, input5, input6, input7, input8, input9;
	private Partner data;
	private Tabel personTable, bankTable;
	
	public Vector<TextBox> boxes;
	
	/**
	 * Constructs the input screen.
	 * 
	 * @param	listener	The action listener to deal with the buttons.
	 */
	public AlterScreen(ActionListener listener) {
		super();

		super.getConstraints().fill = GridBagConstraints.HORIZONTAL;
		
		boxes = new Vector<>();
		
		data = new Partner();
		
		Label title = new Label("Introducere date partener", 20, true);
		super.getConstraints().gridx = 2;
		super.getConstraints().gridy = 0;
		
		super.getConstraints().gridheight = 1;
		super.getConstraints().gridwidth = 1;
		super.getConstraints().fill = GridBagConstraints.HORIZONTAL;
		
		super.getConstraints().insets = new Insets(20,20,20,20);
		super.addComponent(title);
		
		AddPersonButton btn2 = new AddPersonButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 1;
		super.getConstraints().weighty = 1;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = margins;
		super.addComponent(btn2, listener);
		
		AddBankButton btn3 = new AddBankButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = GridBagConstraints.RELATIVE;
		super.getConstraints().insets = margins;
		super.addComponent(btn3, listener);
		
		ContinueButton btn4 = new ContinueButton(20);
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = GridBagConstraints.RELATIVE;
		super.getConstraints().insets = margins;
		super.addComponent(btn4, listener);
		
		BackButton btn1 = new BackButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = GridBagConstraints.RELATIVE;
		super.getConstraints().insets = margins;
		super.addComponent(btn1, listener);
		
		// Right components
		
		input1 = new TextBox("Denumire","");
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 1;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = margins;
		super.addComponent(input1);
		

		input2 = new TextBox("CIF","");
		super.getConstraints().gridx = 2;
		super.getConstraints().gridy = 1;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = new Insets(0,90,20,20);
		super.addComponent(input2);
		
		input3 = new TextBox("Reg. Com.","");
		super.getConstraints().gridx = 3;
		super.getConstraints().gridy = 1;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = new Insets(0,30,20,20);
		super.addComponent(input3);
		
		input4 = new TextBox("Cod ITM","");
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 2;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = new Insets(0,30,20,20);
		super.addComponent(input4);
		
		input5 = new TextBox("Cod CASS","");
		super.getConstraints().gridx = 2;
		super.getConstraints().gridy = 2;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = new Insets(0,40,20,20);
		super.addComponent(input5);
		
		input6 = new TextBox("Cod CAEN","");
		super.getConstraints().gridx = 3;
		super.getConstraints().gridy = 2;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = new Insets(0,30,20,20);
		super.addComponent(input6);
		
		input7 = new TextBox("Adresă","");
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 3;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = new Insets(0,40,20,20);
		super.addComponent(input7);
		
		input8 = new TextBox("Localitate","");
		super.getConstraints().gridx = 2;
		super.getConstraints().gridy = 3;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = new Insets(0,40,20,20);
		super.addComponent(input8);
		
		input9 = new TextBox("Cod poștal","");
		super.getConstraints().gridx = 3;
		super.getConstraints().gridy = 3;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = margins;
		super.addComponent(input9);
		
		String[] option1 = {"PFA", "SRL", "SA"};
		list1 = new DropDown("Tip Firmă", option1);
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 4;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = margins;
		super.addComponent(list1);
		
		String[] option2 = {"PFA", "MICRO", "MACRO"};
		list2 = new DropDown("Tip Activitate", option2);
		super.getConstraints().gridx = 2;
		super.getConstraints().gridy = 4;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = margins;
		super.addComponent(list2);
		
		String[] option3 = {"Nu plătește TVA", "Lunar", "Trimestrial"};
		list3 = new DropDown("TVA", option3);
		super.getConstraints().gridx = 3;
		super.getConstraints().gridy = 4;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = new Insets(0,70,20,20);
		super.addComponent(list3);
		
		String[] option4 = {"Nu are", "Are"};
		list4 = new DropDown("Salariați", option4);
		super.getConstraints().gridx = 4;
		super.getConstraints().gridy = 4;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().insets = margins;
		super.addComponent(list4);
		
		personTable = new Tabel(4);
		personTable.addElement("Nume", true);
		personTable.addElement("Functie", true);
		personTable.addElement("EMail", true);
		personTable.addElement("Telefon", true);
		
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 5;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().gridwidth = 2;
		super.getConstraints().insets = margins;
		super.addComponent(personTable);
		
		bankTable = new Tabel(4);
		bankTable.addElement("Nume", true);
		bankTable.addElement("Filiala", true);
		bankTable.addElement("Cont", true);
		bankTable.addElement("Moneda", true);
		
		super.getConstraints().gridx = 2;
		super.getConstraints().gridy = 5;
		super.getConstraints().anchor = GridBagConstraints.WEST;
		super.getConstraints().gridwidth = 2;
		super.getConstraints().insets = margins;
		super.addComponent(bankTable);
		
		boxes.add(input1);
		boxes.add(input2);
		boxes.add(input3);
		boxes.add(input4);
		boxes.add(input5);
		boxes.add(input6);
		boxes.add(input7);
		boxes.add(input8);
		boxes.add(input9);

	}
	
	/**
	 * Collects the data from the text boxes.
	 * 
	 * This function collects the data from the text boxes
	 * and stores it in a Partner-type container and returns
	 * it for usage.
	 * 
	 * @return	A Partner-type container.
	 */
	public Partner retrieveContent() {
		data.Activity = list2.getText();
		data.Type = list1.getText();
		data.TVA = list3.getText();
		data.Sal = list4.getText();
		data.Address = input7.getText();
		data.Loc = input8.getText();
		data.postalCode = input9.getText();
		
		data.CAEN = input6.getText();
		data.CASS = input5.getText();
		data.CIF = input2.getText();
		data.ITM = input4.getText();
		
		data.Name = input1.getText();
		data.RegCom = input3.getText();
	
		return data;
	}
	
	/**
	 * Refreshes the person table
	 */
	public void refreshTable() {
		personTable.empty();
		personTable.addElement("Nume", true);
		personTable.addElement("Functie", true);
		personTable.addElement("EMail", true);
		personTable.addElement("Telefon", true);
		
		for(Person e : data.contactList) {
			updateTable(e);
		}
	}
	
	/**
	 * This function updates the person display.
	 * 
	 * @param	p	A Person-type filled container.
	 */
	private void updateTable(Person p) {
		personTable.addElement(p.Name, false);
		personTable.addElement(p.Job, false);
		personTable.addElement(p.Email, false);
		personTable.addElement(p.PhoneNumber, false);
	}
	
	/**
	 * This function updates the bank display.
	 * 
	 * @param	b	A Bank-type filled container.
	 */
	public void updateTable(Bank b) {
		bankTable.addElement(b.Name, false);
		bankTable.addElement(b.Loc, false);
		bankTable.addElement(b.Acc, false);
		bankTable.addElement(b.Coin, false);
	}
	
	/**
	 * This function returns a reference to the data container.
	 * 
	 * @return	A Partner-type container for usage.
	 */
	public Partner getData() {
		return data;
	}
	
	/**
	 * Clears all the content.
	 */
	public void reset() {
		input1.reset();
		input2.reset();
		input3.reset();
		input4.reset();
		input5.reset();
		input6.reset();
		input7.reset();
		input8.reset();
		input9.reset();
		
		personTable.empty();
		personTable.addElement("Nume", true);
		personTable.addElement("Functie", true);
		personTable.addElement("EMail", true);
		personTable.addElement("Telefon", true);
		
		bankTable.empty();
		bankTable.addElement("Nume", true);
		bankTable.addElement("Filiala", true);
		bankTable.addElement("Cont", true);
		bankTable.addElement("Moneda", true);
		
		data.Activity = null;
		data.Address = null;
		data.bankList.removeAllElements();
		data.contactList.removeAllElements();
		data.CAEN = null;
		data.CASS = null;
		data.CIF = null;
		data.Country = null;
		data.ITM = null;
		data.Loc = null;
		data.Name = null;
		data.postalCode = null;
		data.RegCom = null;
		data.Sal = null;
		data.TVA = null;
		data.Type = null;
	}
}
