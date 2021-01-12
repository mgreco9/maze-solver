package greco.insidezecube.exception;

/**
 * 
 * @author Martin Greco
 *
 * Describe an error in the maze file when a character encoding isn't recognized
 */
public class MazeEncodingException extends MazeInitializationException {
	
	private static final long serialVersionUID = 1L;

	public MazeEncodingException(String errorMessage) {
		super(errorMessage);
	}
}
