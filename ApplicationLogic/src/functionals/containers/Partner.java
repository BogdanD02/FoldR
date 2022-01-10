package functionals.containers;

import java.util.Vector;

/**
 * This class models a partner company
 * <p>
 * This class holds all the necessary data about
 * the company.
 * </p>
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class Partner {
	/**
	 * The country of the corporation
	 */
	public String Country;
	
	/**
	 * The name of the corp
	 */
	public String Name;
	
	/**
	 * For firms in Romania: Fiscal Code
	 */
	public String CIF;
	
	/**
	 * For firms in Romania: The nr from the Commerce Registry
	 */
	public String RegCom;
	
	/**
	 * For firms in Romania: The CASS, ITM and CAEN codes
	 */
	public String ITM, CASS, CAEN;
	
	/**
	 * The firm's address
	 */
	public String Address;
	
	/**
	 * The firm's city
	 */
	public String Loc;
	
	/**
	 * For firms in Romania: The type of firm (PFA, SRL, SA, ONG)
	 */
	public String Type;
	
	/**
	 * For firms in Romania: The type of activity (PFA, ONG, MICRO, MACRO)
	 */
	public String Activity;
	
	/**
	 * For firms in Romania: The period for TVA payment (None, Monthly, Quarterly)
	 */
	public String TVA;
	
	/**
	 * If the firm has employees
	 */
	public String Sal;
	
	/**
	 * The postal code of the firm
	 */
	public String postalCode;
	
	/**
	 * A list of contact persons for the firm.
	 */
	public Vector<Person> contactList;
	
	/**
	 * A list of banks
	 */
	public Vector<Bank> bankList;
	
	public Partner() {
		contactList = new Vector<>();	
		bankList = new Vector<>();
	}

}
