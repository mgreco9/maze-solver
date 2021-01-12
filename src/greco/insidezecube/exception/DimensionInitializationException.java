package greco.insidezecube.exception;

/**
 * 
 * @author Martin Greco
 *
 * Describe an exception occuring when the measured size of the maze matrix doesn't corresponds
 * to the initial dimension given at the beginning of the file.
 */
public class DimensionInitializationException extends MazeInitializationException {

	private static final long serialVersionUID = 1L;

	public DimensionInitializationException(String errorMessage) {
		super(errorMessage);
	}
}
