/**
 * 
 */
package graphics.ui;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

/**
 * This class models an abstract object of the graphic interface
 * <p>
 * The following class represents a foundation for the derived classes
 * and provides a series of methods which those classes must implement
 * in order to be considered valid graphic interface objects.
 * </p>
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public abstract class InterfaceObject {
	protected JPanel Parent;
	
	/**
	 * This function checks an abstract object reference against the null value
	 * 
	 * @param	<RefType>	Abstract type denoting the type of the reference
	 * 						being checked.
	 * 
	 * @param	obj			The actual reference being checked
	 * 
	 * @return	True if the object is not null and False otherwise.
	 */
	protected <RefType> boolean isValidReference(RefType obj) {
		return !(obj == null);
	}
	
	/**
	 * Creates a new InterfaceObject with no parent.
	 */
	public InterfaceObject() {
		Parent = null;
	}
	
	/**
	 * Adds the object's model to the Panel received as argument
	 * 
	 * If the object received as argument represents a valid reference
	 * (is not null) it attaches the model to it while keeping a reference
	 * of the object in case the model needs to be detached.
	 * 
	 * @param	container	A reference to the panel which will
	 * 						contain this label
	 * 
	 * @param	c			The constraints of the component being added
	 * 
	 * @exception	NullPointerException	This is thrown when the function is
	 * 										invoked on a null object.
	 */
	public abstract void attachObject(JPanel container, GridBagConstraints c) throws NullPointerException;
	
	/**
	 * This function detaches the model from the Panel holding it.
	 * 
	 * If the model is attached to a panel, it detaches it. It does
	 * nothing if the model is not attached.
	 */
	public abstract void detachObject();
}
