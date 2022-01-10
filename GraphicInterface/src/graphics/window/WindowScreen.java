/**
 * 
 */
package graphics.window;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;

import graphics.ui.InterfaceObject;
import graphics.ui.buttons.Button;

/**
 * This class models a user interface menu
 * <p>
 * This class combines the UI elements into one common container
 * and is responsible for managing them. (attaching, detaching and
 * modifying the UI components).
 * </p>
 * 
 * @version 1.1.0
 * @author David Bogdan
 * @since 1.0.0
 */
@SuppressWarnings("exports")
public class WindowScreen {
	protected static Insets margins = new Insets(0,20,20,20);
	
	public Vector<Button> ContainedButtons = new Vector<>();
	private JPanel Container;
	
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	/**
	 * Creates an empty screen
	 */
	public WindowScreen() {
		this.Container = new JPanel();
		this.layout = new GridBagLayout();
		this.constraints = new GridBagConstraints();
		
		Container.setMinimumSize(new Dimension(300,300));
		Container.setLayout(layout);
	}
	
	/**
	 * Adds a new UI component to the screen
	 * 
	 * @param	obj		An InterfaceObject reference
	 */
	public void addComponent(InterfaceObject obj) {
		obj.attachObject(Container, constraints);
	}
	
	/**
	 * Adds a new button to the screen
	 * 
	 * @param	obj			The button being added
	 * @param	listener	An action listener for the onClick functionality
	 */
	public void addComponent(Button obj, ActionListener listener) {
		obj.attachObject(Container, constraints);
		obj.addActionListner(listener);
		
		ContainedButtons.add(obj);
	}
	
	/**
	 * Returns a reference to the container
	 * 
	 * @return	A JPanel object denoting the container
	 */
	public JPanel getContainer() {
		return Container;
	}
	
	/**
	 * Returns a reference to the panel constraints,
	 * so that the component can be properly aligned.
	 * 
	 * @return	A reference to the panel constraints.
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}
}
