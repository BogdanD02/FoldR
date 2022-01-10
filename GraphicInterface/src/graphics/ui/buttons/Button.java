/**
 * 
 */
package graphics.ui.buttons;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import graphics.ui.InterfaceObject;
import graphics.ui.exceptions.InvalidPanelException;

/**
 * This class models an abstract button.
 * <p>
 * While this class provides implementations for the abstract methods
 * inherited from InterfaceObjects, it also introduces new abstract methods
 * such as onClick(). Furthermore, the class provides an extension
 * of the ActionListener interface, requiring derived classes to implement
 * it as well.
 * </p>
 * 
 * @version 2.0.0
 * @author David Bogdan
 * @since 1.2.0
 */
public abstract class Button extends InterfaceObject {
	/**
	 * The derived buttons need access to the panels so they can perform actions
	 * which affect larger components (e.g. RenderWindow, the panel, etc.)
	 */
	protected JPanel Parent;
	
	/**
	 * The model is kept protected as derived buttons will
	 * implement different characteristics of the model, therefore
	 * access to the Model is required.
	 */
	protected JButton Model;
	
	/**
	 * Instantiates a new Button model
	 */
	public Button() {
		// Hence the Model is different for every derived class,
		// it is not initialized during base class construction
		
		Parent = null;
	}
	
	@Override
	public void attachObject(JPanel container, GridBagConstraints c) throws NullPointerException {
		if(!isValidReference(container))
			throw new InvalidPanelException("Attempting to attach button to a NULL panel!");
		container.add(Model, c);
		Parent = container;
	}

	@Override
	public void detachObject() {
		if(isValidReference(Parent))
			Parent.remove(Model);
		Parent = null;
	}
	
	/**
	 * Adds an action listener to the model.
	 * 
	 * @param	listener	The action listener
	 */
	public void addActionListner(ActionListener listener) {
		Model.addActionListener(listener);
	}
	
	/**
	 * Returns a final reference to the model
	 * 
	 * @return The obj model.
	 */
	public final JButton getModel() {
		return Model;
	}
	
	/**
	 * Abstract method denoting the action to be performed on click
	 */
	public abstract int onClick();
	
	/**
	 * Abstract method denoting the action to be performed on focus
	 */
	public abstract void onFocus();
}
