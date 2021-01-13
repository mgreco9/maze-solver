package greco.insidezecube.maze;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	
	public static void exportMaze(String path) {
		Pair<Integer, Integer> mazeDimension = getMazeDimension();
		String mazeText = myMaze.toString();
		
		FileWriter myWriter;
		try {
			myWriter = new FileWriter("layouts/"+path);
			myWriter.write(mazeDimension.toString());
			myWriter.write("\n");
			myWriter.write(mazeText);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Pair<Integer,Integer> getMazeDimension(){
		return new Pair<Integer, Integer>(myMaze.getLength(),myMaze.getWidth());
	}
	
	public static String getMazeName() {
		return myMaze.getName();
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
