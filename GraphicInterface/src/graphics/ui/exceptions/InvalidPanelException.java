/**
 * 
 */
package graphics.ui.exceptions;

/**
 * This exception gets thrown whenever a GUI Object is attached
 * to a invalid (null) JPanel container.
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class InvalidPanelException extends NullPointerException {
	/**
	 * The class's serial version ID used during serialization
	 * 
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 8300079779249706494L;

	public InvalidPanelException() {
		super();
	}
	
	public InvalidPanelException(String message) {
		super(message);
	}
}
