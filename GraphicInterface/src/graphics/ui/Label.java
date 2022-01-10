/**
 * 
 */
package graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import graphics.ui.exceptions.InvalidPanelException;

/**
 * This class models a text area in the user interface
 * 
 * @version 1.2.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class Label extends InterfaceObject{

	private JLabel Model;
	
	/**
	 * This represents the default font of all labels. It has been declared as static
	 * so it will not consume unnecessary memory when multiple Labels are instantiated.
	 */
	private static final Font BASE_FONT = new Font("Times New Roman", Font.PLAIN, 14);
	
	/**
	 * Class constructor
	 * 
	 * @param	text		The text of the label
	 * @param	charSize	The size of the characters
	 * @param	isBold		Whether the text should be in bold or not
	 */
	public Label(String text, int charSize, boolean isBold) {
		super();
		
		Model = new JLabel();
		
		// Setting specifics
		Model.setText(text);
		
		// Setting font and color
		if(isBold)
			Model.setFont(new Font("Times New Roman", Font.BOLD, charSize));
		else if (charSize != 14)
			Model.setFont(new Font("Times New Roman", Font.PLAIN, charSize));
		else
			Model.setFont(BASE_FONT);
		Model.setForeground(Color.BLACK);
	}
	
	/**
	 * {@code isBold} defaults to False
	 * 
	 *  @see Label#Label(String, int, boolean)
	 */
	public Label(String text, int charSize) {
		super();
		
		Model = new JLabel();
		
		// Setting specifics
		Model.setText(text);
		
		// Setting font and color
		if (charSize != 14)
			Model.setFont(new Font("Times New Roman", Font.PLAIN, charSize));
		else
			Model.setFont(BASE_FONT);
		Model.setForeground(Color.BLACK);
	}
	
	/**
	 * {@code charSize} defaults to 14
	 * 
	 *  @see Label#Label(String, int, boolean)
	 */
	public Label(String text, boolean isBold) {
		super();
		
		Model = new JLabel();
		
		// Setting specifics
		Model.setText(text);
		
		// Setting font and color
		if(isBold)
			Model.setFont(new Font("Times New Roman", Font.BOLD, 14));
		else
			Model.setFont(BASE_FONT);
		Model.setForeground(Color.BLACK);
	}
	
	/**
	 * {@code charSize} defaults to 14 and {@code isBold} defaults to False
	 * 
	 * @see Label#Label(String, int, boolean)
	 */
	public Label(String text) {
		super();
		
		Model = new JLabel();
		
		// Setting specifics
		Model.setText(text);
		
		// Setting font and color
		Model.setFont(BASE_FONT);
		Model.setForeground(Color.BLACK);
	}
	
	/**
	 * Adds the Model of the label to the Panel received as argument
	 * 
	 * If the object received as argument represents a valid reference
	 * (is not null) it attaches the Label to it while keeping a reference
	 * of the object in case the Label needs to be detached.
	 * 
	 * @param	container	A reference to the panel which will
	 * 						contain this label
	 * 
	 * @param	c			The constraints of the component being added
	 * 
	 * @exception	NullPointerException	This is thrown when the function is
	 * 										invoked on a null object.
	 * @see InterfaceObject#attachObject(JPanel)
	 */
	public void attachObject(JPanel container, GridBagConstraints c) throws InvalidPanelException {
		if(!isValidReference(container))
			throw new InvalidPanelException("Attempting to attach label to null Panel");
		container.add(Model, c);
		this.Parent = container;
	}
	
	/**
	 * This function detaches the Label from the Panel holding it.
	 * 
	 * If the Label is attached to a panel, it detaches it. It does
	 * nothing if the label is not attached.
	 * 
	 * @see InterfaceObject#detachObject()
	 */
	public void detachObject() {	
		if(isValidReference(Parent)) {
			Parent.remove(Model);
			Parent = null;
		}
	}
	
	/**
	 * Changes the color of the text
	 * 
	 * @param	color	The new color of the text
	 * 					can be in RGB format or a code (e.g. Color.Black)
	 */
	public void changeColor(Color color) {
		this.Model.setForeground(color);
	}
}
