package graphics.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.ui.exceptions.InvalidPanelException;

/**
 * This class models an input field.
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.3.0
 */
public class TextBox extends InterfaceObject {
	private JTextField TextArea;
	private JPanel Model;
	private Label FieldName;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	public TextBox(String fieldName, String defaultText) {
		this.TextArea = new JTextField();
		this.TextArea.setText(defaultText);
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
	 * Returns the text in the area.
	 * 
	 * @return	The text in the field
	 */
	public String getText() {
		return TextArea.getText();
	}
	
	/**
	 * This function empties the text area.
	 */
	public void reset() {
		TextArea.setText("");
		TextArea.setEnabled(true);
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
	
	/**
	 * Used when getting data automatically
	 * 
	 * @param	value	The retrieved value
	 */
	public void lock(String value) {
		TextArea.setText(value);
		TextArea.setEnabled(false);
	}

}
