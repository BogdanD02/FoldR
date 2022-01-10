/**
 * 
 */
package graphics.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

import functionals.containers.Bank;
import functionals.containers.Partner;
import functionals.containers.Person;
import functionals.handlers.DBHandler;
import functionals.handlers.WebHandler;
import functionals.validators.ContentValidator;
import graphics.ui.buttons.*;
import graphics.window.screens.*;

/**
 * This class models the main application
 * <p>
 * This class is responsible for the graphics as a whole and can only have one
 * object inside the main class. It deals directly with the frame and controls
 * which objects are rendered to it, as well as how they respond to different
 * events.
 * </p>
 * 
 * @version 1.0.1
 * @author David Bogdan
 * @since 1.0.0
 */
public final class ApplicationWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private Vector<WindowScreen> ScreenVector = new Vector<>();
	private Vector<Integer> VisitedScreens;
	private WindowScreen ActiveScreen;
	private ContentValidator validator;
	private DBHandler handler;
	private WebHandler crawler;
	
	/**
	 * This function constructs all the menus.
	 * 
	 * The following function is called only once at construction,
	 * and builds all the necessary WindowScreen objects while
	 * storing them inside a container.
	 * 
	 * TO BE MODIFIED
	 */
	private void buildScreens() {
		ScreenVector.add(new MainScreen(this));
		ScreenVector.add(new AlterScreen(this));
		ScreenVector.add(new AddPersonScreen(this));
		ScreenVector.add(new AddBankScreen(this));
		
	}
	
	/**
	 * This function removes all components of the previous screen
	 * from the render window and replaces them with the current
	 * active screen.
	 */
	private void update() {
		this.add(ActiveScreen.getContainer());
		this.pack();
	}
	
	/**
	 * Clears the content of the screen if necessary
	 */
	private void resetContent() {
		switch(ScreenVector.indexOf(ActiveScreen)) {
		case 1:
			((AlterScreen)ActiveScreen).reset();
			break;
		case 2:
			((AddPersonScreen)ActiveScreen).reset();
			break;
		case 3:
			((AddBankScreen)ActiveScreen).reset();
			break;
		default:
			break;
		}
	}
	
	/**
	 * Adds a person to the input data.
	 */
	private void addPerson() {
		Person p = ((AddPersonScreen)ActiveScreen).getData();
		resetContent();
		
		this.switchScreen(VisitedScreens.elementAt(0));
		this.VisitedScreens.remove(0);
		
		((AlterScreen)ActiveScreen).getData().contactList.add(p);
		((AlterScreen)ActiveScreen).refreshTable();
		
		this.remove(ActiveScreen.getContainer());
		update();
	}
	
	/**
	 * Adds a bank to the input data.
	 */
	private void addBank() {
		Bank b = ((AddBankScreen)ActiveScreen).getData();
		resetContent();
		
		this.switchScreen(VisitedScreens.elementAt(0));
		this.VisitedScreens.remove(0);
		
		((AlterScreen)ActiveScreen).getData().bankList.add(b);
		((AlterScreen)ActiveScreen).updateTable(b);
		
		this.remove(ActiveScreen.getContainer());
		update();
	}
	
	private void refreshTable(ResultSet rs) {		
		try {
			((MainScreen)ActiveScreen).refreshTable();
			
			while(rs.next()) {
				((MainScreen)ActiveScreen).addElement(rs.getString("CIF"));
				((MainScreen)ActiveScreen).addElement(rs.getString("Denumire"));
				((MainScreen)ActiveScreen).addElement(rs.getString("Oras"));
				((MainScreen)ActiveScreen).addElement(rs.getString("Cod_CASS"));
			}
		} catch (Exception e) {
			
		}
		
		this.remove(ActiveScreen.getContainer());
		update();
	}
	
	/**
	 * This function performs a specific action based
	 * on the code it has received.
	 * 
	 * @param	ACTION_CODE		The code of the performed action.
	 */
	private void performAction(int ACTION_CODE) {
		String CIF;	// CIF is stored here because there are more functionalities
					// which depend on it.
		
		switch(ACTION_CODE) {
		case 10:	
			this.dispose();
			break;
			
		case 11:	
			resetContent();
			
			this.switchScreen(VisitedScreens.elementAt(0));
			this.VisitedScreens.remove(0);
			break;
		
		case 12:	
			CIF = JOptionPane.showInputDialog("Introduceti codul CIF: ");
			
			if( CIF != null && !validator.isValidCIF(CIF) && !validator.isValidCNP(CIF) )
				JOptionPane.showMessageDialog(null, "Ați introdus un cod CIF invalid!", "Eroare", JOptionPane.WARNING_MESSAGE);
			else if ( CIF != null ) {
				String response = crawler.executePost(CIF);
				
				JSONObject obj = new JSONObject(response);
				if(obj.getInt("cod") == 200) {
					JSONArray datarr = obj.getJSONArray("found");
					JSONObject data = datarr.getJSONObject(0);
					
					((AlterScreen)(ScreenVector.elementAt(1))).boxes.elementAt(1).lock(CIF);
					((AlterScreen)(ScreenVector.elementAt(1))).boxes.elementAt(0).lock(data.getString("denumire"));
					((AlterScreen)(ScreenVector.elementAt(1))).boxes.elementAt(2).lock(data.getString("nrRegCom"));
				
					String[] addr = data.getString("adresa").split(" ");
					StringBuffer buffer = new StringBuffer();
					
					for(int i = 4; i < addr.length; i++) {
						buffer.append(addr[i]);
						buffer.append(" ");
					}
					((AlterScreen)(ScreenVector.elementAt(1))).boxes.elementAt(7).lock(addr[3].replace(',', '\0'));
					((AlterScreen)(ScreenVector.elementAt(1))).boxes.elementAt(6).lock(buffer.toString());
				} else {
					JOptionPane.showMessageDialog(null, "Nu s-a putut comunica cu serverul ANAF.", "Eroare", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			break;
		
		case 25: 
			addPerson();
			break;
			
		case 26: 
			addBank();
			break;
				
		case 30:
			
			refreshTable(handler.getDecl());
			break;
		case 31:	
			Partner p = ((AlterScreen)ActiveScreen).retrieveContent();
			boolean ok = true;
			
			for(Person per : p.contactList) {
				if(!validator.isValidCNP(per.CNP)) {
					JOptionPane.showMessageDialog(null, "Persoana " + per.Name + " are CNP invalid!", "Eroare", JOptionPane.WARNING_MESSAGE);
					p.contactList.remove(per);
					ok = false;
					break;
				}
			}
			
			if(ok) {
				handler.insertData( ((AlterScreen)ActiveScreen).retrieveContent() );
			
				resetContent();
				this.VisitedScreens.removeAllElements();
				this.switchScreen(0);
			}
			break;
			
		case 32:
			ResultSet rs = handler.getDecl();
			JFileChooser browser = new JFileChooser();
			int response = browser.showSaveDialog(null);
			
			if(response == 0) {
				String path = browser.getSelectedFile().getAbsolutePath();
				Document doc = new Document();
				
				try {
					StringBuffer buffer = new StringBuffer();
					PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
					doc.open();
					
					while(rs.next()) {
						Paragraph paragraph = new Paragraph();
						paragraph.setAlignment(Element.ALIGN_LEFT);
						paragraph.add(rs.getString("Denumire"));
						
						paragraph.add("\n");
						paragraph.add("Trebuie predate declaratiile: 100, ");
						
						if(rs.getInt("Salariati") == 1) {
							paragraph.add("112, ");
						}
						
						if(rs.getString("TVA") == "Lunar" || rs.getString("TVA") == "Trimestrial") {
							paragraph.add("300, ");
						}
						
						paragraph.add("390, ");
						paragraph.add("392, ");
						paragraph.add("394, ");
						
						paragraph.add("\n");
						doc.add(paragraph);
					}
					doc.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
		
		if(ACTION_CODE > 11 && ACTION_CODE < 25) {
			this.VisitedScreens.insertElementAt(ScreenVector.indexOf(ActiveScreen), 0);
			this.switchScreen(ACTION_CODE - 11);
		}
	}
	
	/**
	 * Creates the application window.
	 */
	public ApplicationWindow() {
		handler = new DBHandler();
		crawler = new WebHandler();
		
		this.setTitle("FoldR");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		buildScreens();
		VisitedScreens = new Vector<>();
		ActiveScreen = ScreenVector.elementAt(0);
		validator = new ContentValidator();
		
		// The size of the window is always the size of the active screen
		this.add(ActiveScreen.getContainer());
		this.pack();
		
		refreshTable(handler.getDecl());
	}
	
	/**
	 * This function commutes between 2 WindowScreens
	 * 
	 * When called, this function updates the current active screen
	 * with the one given as argument and sends a signal by calling
	 * the update() function to refresh the render screen.
	 * 
	 * @param	screenIndex		The new index of the new active screen
	 */
	public void switchScreen(int screenIndex) {
		this.remove(ActiveScreen.getContainer());
		
		ActiveScreen = ScreenVector.elementAt(screenIndex);
		
		update();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(Button btn : ActiveScreen.ContainedButtons) {
			if(arg0.getSource() == btn.getModel()) {
				performAction(btn.onClick());
			}
		}
	}
}
