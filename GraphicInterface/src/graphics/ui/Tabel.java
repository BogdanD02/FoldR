package graphics.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JPanel;

import graphics.ui.exceptions.InvalidPanelException;

/**
 * This class creates a new table
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.2.0
 */
public final class Tabel extends InterfaceObject {
	private JPanel Model;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	
	private int cols;
	private int CurrentRow;
	private int CurrentCol;
	
	private Vector<Label> Content;
	
	/**
	 * Updates the constraints for the next element to be inserted.
	 */
	private void updateCurrents() {
		CurrentCol++;
		
		if(CurrentCol == cols) {
			CurrentCol = 0;
			CurrentRow++;
		}
	}
	
	/**
	 * Creates a new table supporting a maximum number of rows
	 * and columns.
	 * 
	 * @param	MAX_ROWS	The maximum number of rows
	 * @param	MAX_COLS	The maximum number of columns
	 */
	public Tabel(int MAX_COLS) {
		this.Model = new JPanel();
		this.layout = new GridBagLayout();
		this.constraints = new GridBagConstraints();
		this.Content = new Vector<>();
		
		this.cols = MAX_COLS;
		this.CurrentRow = 0;
		this.CurrentCol = 0;
		
		constraints.insets = new Insets(10,10,10,10);
		
		//Model.setSize(300, 300);
		Model.setLayout(layout);
	}
	
	/**
	 * Adds a new element in the table
	 * 
	 * @param	text		The text of the element
	 * @param	isHeader	If the element is a column header.
	 */
	public void addElement(String text, boolean isHeader) {
		Label temp = new Label(text, isHeader);
		
		constraints.gridx = CurrentCol;
		constraints.gridy = CurrentRow;
		
		Content.add(temp);
		temp.attachObject(Model, constraints);
	
		updateCurrents();
	}
	
	/**
	 * Empties the table of all its content.
	 */
	public void empty() {
		for(Label el : Content) {
			el.detachObject();
		}
		
		Content.removeAllElements();
		CurrentRow = 0;
		CurrentCol = 0;
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

}
