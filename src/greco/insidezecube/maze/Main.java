package greco.insidezecube.maze;

import java.awt.EventQueue;

import greco.insidezecube.exception.MazeInitializationException;
import greco.insidezecube.gui.MainGui;
import greco.insidezecube.utils.Pair;

public class Main {
	
	private static Maze2D myMaze;

	public static void main(String[] args) {
		instantiateDefaultMaze();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void instantiateDefaultMaze() {
		try {
			myMaze = new Maze2D();
		} catch (MazeInitializationException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static void instantiateMaze(String path) {
		try {
			myMaze = new Maze2D(path);
		} catch (MazeInitializationException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static Pair<Integer,Integer> getMazeDimension(){
		return new Pair<Integer, Integer>(myMaze.getLength(),myMaze.getWidth());
	}
	
	public static boolean[][] getPrintableMaze(){
		return myMaze.getMazeLayout();
	}
	
	public static boolean getCellType(int row, int col) {
		return myMaze.getMazeCell(row, col);
	}
	
	public static void changeCell(int row, int col) {
		myMaze.changeCell(row,col);
	}

}
