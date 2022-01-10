package functionals.containers;

/**
 * This class models an ID card
 * <p>
 * This class holds all the necessary data about
 * ID cards in the Romanian format.
 * </p>
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class Identification {
	/**
	 * The series of the ID (e.g. TZ)
	 */
	public String Series;
	
	/**
	 * The emitting institution (e.g. SPCLEP TIMISOARA)
	 */
	public String Inst;
	
	/**
	 * The release date (e.g. 02/10/2020)
	 */
	public String Date;
	
	/**
	 * The number of the ID (e.g. 191945)
	 */
	public int Number;
	
	public Identification() {
		// TODO Auto-generated constructor stub
	}

}
