package greco.insidezecube.exception;

/**
 * 
 * @author Martin Greco
 *
 * Triggers when an empty file is given.
 */
public class EmptyFileException extends MazeInitializationException {

	private static final long serialVersionUID = 1L;

	public EmptyFileException(String errorMessage) {
		super(errorMessage);
	}
}

