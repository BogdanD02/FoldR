package functionals.containers;

/**
 * This class models a contact person
 * <p>
 * This class holds all the necessary data about
 * a contact person (e.g. name, mail, phone number)
 * and provides methods for data validation.
 * </p>
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class Person {
	/**
	 * The name of the person
	 */
	public String Name;
	
	/**
	 * The job within the firm
	 */
	public String Job;
	
	/**
	 * The phone number
	 */
	public String PhoneNumber;
	
	/**
	 * The email
	 */
	public String Email;
	
	/**
	 * For people in Romania: Details about their ID card
	 */
	public Identification ID;
	
	/**
	 * Denotes whether the person is gets notifications or not
	 */
	public boolean isNotified;
	
	/**
	 * For people in Romania: their CNP.
	 */
	public String CNP;
	
	public Person() {
		// TODO Auto-generated constructor stub
		
		ID = new Identification();
	}

}
