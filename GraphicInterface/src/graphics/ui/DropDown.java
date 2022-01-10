package graphics.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import graphics.ui.exceptions.InvalidPanelException;

/**
 * This class models a drop-down menu.
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.3.0
 */
public final class DropDown extends InterfaceObject {
	private JComboBox<String> TextArea;
	private JPanel Model;
	private Label FieldName;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	public DropDown(String fieldName, String[] options) {
		this.TextArea = new JComboBox<String>(options);
		this.TextArea.setPreferredSize(new Dimension(150, 30));
		
		this.layout = new GridBagLayout();
		this.constraints = new GridBagConstraints();
		this.FieldName = new Label(fieldName);
		this.Model = new JPanel();
		
		Model.setLayout(layout);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10,10,10,10);
		FieldName.attachObject(Model, constraints);
	
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridy = 0;
		constraints.insets = new Insets(10,10,10,10);
		Model.add(TextArea);
	}

	/**
	 * Returns the text in the field.
	 * 
	 * @return	The text in the field
	 */
	public String getText() {
		return TextArea.getItemAt(TextArea.getSelectedIndex());
	}
	
	@Override
	public void attachObject(JPanel container, GridBagConstraints c) throws NullPointerException {
		if(!isValidReference(container))
			throw new InvalidPanelException("Attempting to attach label to null Panel");
		container.add(Model, c);
		this.Parent = container;
	}

	@Override
	public void detachObject() {
		if(isValidReference(Parent)) {
			Parent.remove(Model);
			Parent = null;
		}
	}

}
