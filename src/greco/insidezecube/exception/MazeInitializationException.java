package greco.insidezecube.exception;

/**
 * 
 * @author Martin Greco
 *
 * Encapsulate every possible maze Exception during initialization
 */
public class MazeInitializationException extends Exception{

	private static final long serialVersionUID = 1L;

	public MazeInitializationException(String errorMessage) {
		super(errorMessage);
	}
	
	public MazeInitializationException(Exception e) {
		super(e);
	}
	
	public MazeInitializationException(String errorMessage, Exception e) {
		super(errorMessage, e);
	}
}