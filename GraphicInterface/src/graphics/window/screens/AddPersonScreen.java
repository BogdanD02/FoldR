package graphics.window.screens;

import java.awt.Insets;
import java.awt.event.ActionListener;

import functionals.containers.Person;
import graphics.ui.TextBox;
import graphics.ui.buttons.BackButton;
import graphics.ui.buttons.SaveButton;
import graphics.window.WindowScreen;

/**
 * This class models an extension of the the input menu screen.
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class AddPersonScreen extends WindowScreen {
	private TextBox nume, functie, telefon, email, cnp, serie, numar, data, emis;
	
	public AddPersonScreen(ActionListener listener) {
		super();
		
		nume = new TextBox("Nume: ", "");
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 0;
		super.getConstraints().gridwidth = 1;
		super.getConstraints().gridheight = 1;
		super.getConstraints().insets = new Insets(20,30,20,20);
		super.addComponent(nume);
		
		functie = new TextBox("Functie: ", "");
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 0;
		super.getConstraints().insets = new Insets(20,30,20,20);
		super.addComponent(functie);
	
		telefon = new TextBox("Telefon: ", "");
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 1;
		super.getConstraints().insets = margins;
		super.addComponent(telefon);
		
		email = new TextBox("EMail: ", "");
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 1;
		super.getConstraints().insets = new Insets(0,50,20,20);
		super.addComponent(email);
		
		cnp = new TextBox("CNP: ", "");
		super.getConstraints().gridx = 2;
		super.getConstraints().gridy = 1;
		super.getConstraints().insets = margins;
		super.addComponent(cnp);
		
		serie = new TextBox("Serie CI: ", "");
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 2;
		super.getConstraints().insets = margins;
		super.addComponent(serie);
		
		numar = new TextBox("Numar CI: ", "");
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 2;
		super.getConstraints().insets = margins;
		super.addComponent(numar);
		
		data = new TextBox("Data: ", "");
		super.getConstraints().gridx = 2;
		super.getConstraints().gridy = 2;
		super.getConstraints().insets = margins;
		super.addComponent(data);
		
		emis = new TextBox("Emis de: ", "");
		super.getConstraints().gridx = 3;
		super.getConstraints().gridy = 2;
		super.getConstraints().insets = margins;
		super.addComponent(emis);
		
		BackButton backBtn = new BackButton();
		super.getConstraints().gridx = 0;
		super.getConstraints().gridy = 3;
		super.getConstraints().insets = margins;
		super.addComponent(backBtn, listener);
		
		SaveButton saveBtn = new SaveButton();
		super.getConstraints().gridx = 1;
		super.getConstraints().gridy = 3;
		super.getConstraints().insets = margins;
		super.addComponent(saveBtn, listener);
	}
	
	/**
	 * Collects the data stored in text boxes.
	 * 
	 * This function collects all the data stored in text
	 * boxes and puts it in a specific container, returning it.
	 * 
	 * @return	A Person-type container.
	 */
	public Person getData() {
		Person p = new Person();
		
		p.CNP = cnp.getText();
		p.Email = email.getText();
		
		p.ID.Date = data.getText();
		p.ID.Inst = emis.getText();
		p.ID.Number = Integer.parseInt(numar.getText());
		p.ID.Series = serie.getText();
		
		p.isNotified = true;
		p.Job = functie.getText();
		p.Name = nume.getText();
		p.PhoneNumber = telefon.getText();
		
		return p;
	}
	
	/**
	 * Clears the content of the text fields.
	 */
	public void reset() {
		cnp.reset();
		email.reset();
		data.reset();
		emis.reset();
		numar.reset();
		serie.reset();
		functie.reset();
		nume.reset();
		telefon.reset();
	}
}
