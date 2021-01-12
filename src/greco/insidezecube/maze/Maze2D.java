package greco.insidezecube.maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import greco.insidezecube.exception.*;

public class Maze2D extends Maze {
	
	protected final boolean [][] mazeLayout;
	
	/**
	 * Instantiate a 2D maze with the default configuration
	 * @throws MazeInitializationException 
	 */
	public Maze2D() throws MazeInitializationException {
		this.height = 1;
		
		mazeLayout = setMazeConfiguration(LAYOUTPATH+"default.txt");
	}
	
	/**
	 * Instantiate a 2D maze with the given layout model
	 * @param modelName : name of the layout model to use
	 * @throws MazeInitializationException 
	 */
	public Maze2D(String modelName) throws MazeInitializationException {
		this.height = 1;
		
		mazeLayout = setMazeConfiguration(LAYOUTPATH+modelName);
	}
	
	/**
	 * Set the maze configuration based on the given layout model
	 * @param modelPath : path to the layout model
	 * @throws MazeInitializationException 
	 */
	private boolean[][] setMazeConfiguration(String modelPath) throws MazeInitializationException {
		File layoutFile = new File(modelPath);
		BufferedReader layoutFileReader = null;
		try {
			//Open BufferedReader
			layoutFileReader = new BufferedReader(new FileReader(layoutFile));
			
			//If first line is null, file is empty, throw error
			String line = layoutFileReader.readLine();
			if (line == null) {
				throw new EmptyFileException("Initialization error : file is empty");
			}
			
			//First line of the file is set to define maze dimension
			String[] dimensions = line.split(" ");
			if (dimensions.length != 2) {
				throw new DimensionInitializationException("Initialization error : dimension informations could not be parsed correctly");
			}
			
			this.length = Integer.parseInt(dimensions[0]);
			this.width = Integer.parseInt(dimensions[1]);
			
			boolean[][] layout = new boolean[this.length][this.width];
			
			//iterate through every line of the file to fill our maze layout
			int row = 0;
			while((line = layoutFileReader.readLine()) != null) {
				//Check if line length corresponds to width
				if (line.length() != this.width) {
					throw new DimensionInitializationException("Dimension error : line "+(row+1)+" with width "+line.length()+" mismatch expected width ("+this.width+")");
				}
				for (int col = 0; col < this.length; col++) {
					//IS EMPTY PATH
					if(line.charAt(col) == LAYOUTPATHENCODING) {
						layout[row][col] = true;
					}
					//IS WALL
					else if (line.charAt(col) == LAYOUTWALLENCODING) {
						layout[row][col] = false;
					}
					else {
						throw new MazeEncodingException("Parsing error : character " +line.charAt(col)+
								" at line " +(row+1)+ " column " + (col+1) +" is incorrect");
					}
				}
				row++;
			}
			//Check if number of lines corresponds to length
			if (row != this.length) {
				throw new DimensionInitializationException("Dimension error : number of lines ("+row+") mismatch expected length ("+this.length+")");
			}
			return layout;
			
		} catch (FileNotFoundException e) {
			throw new MazeInitializationException("layout file could not be found",e);
		} catch (IOException e) {
			throw new MazeInitializationException("error parsing the layout file",e);
		} finally {
			//Close BufferedReader
			if (layoutFileReader != null) {
				try {
					layoutFileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String toString() {
		//Print whole maze
		String s = "";
		for (int row = 0; row<width; row++) {
			for (int col = 0; col<length; col++) {
				if (mazeLayout[row][col]) {
					s+=".";
				}
				else {
					s+="%";
				}
			}
			s+="\n";
		}
		return s;
	}

	public boolean[][] getMazeLayout() {
		return mazeLayout;
	}
	
	public boolean getMazeCell(int row, int col) {
		return mazeLayout[row][col];
	}
	
	public void changeCell(int row, int col) {
		mazeLayout[row][col] = !mazeLayout[row][col];
	}
	
}
