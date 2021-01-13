package greco.insidezecube.maze;

/**
 * 
 * @author Martin Greco
 *
 * Set the default parameters for a maze
 */
public abstract class Maze {
	protected final String LAYOUTPATH = "layouts/";
	protected final char LAYOUTWALLENCODING = '%';
	protected final char LAYOUTPATHENCODING = '.';
	
	protected int length;
	protected int width;
	protected int height;
	
	protected String name;
	
	public Maze() {
		
	}
	
	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract String toString();
}
