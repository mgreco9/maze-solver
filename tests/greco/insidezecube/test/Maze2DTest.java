package greco.insidezecube.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import greco.insidezecube.exception.*;
import greco.insidezecube.maze.Maze2D;

class Maze2DTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Check if Maze2D instantiate the default layout by default
	 */
	@Test
	void testDefaultConfig() {
		boolean[][] ref = {{true,false,false,false,false,false,false,false,false,false},
						   {true,true,true,true,true,false,false,true,true,false},
						   {false,false,false,false,true,false,false,false,true,false},
						   {false,true,true,true,true,true,true,true,true,false},
						   {false,true,false,false,false,false,false,true,false,false},
						   {false,true,true,true,true,true,false,true,false,true},
						   {false,false,false,false,false,false,false,true,false,true},
						   {true,true,false,true,true,true,true,true,true,true},
						   {false,true,false,true,false,true,false,false,false,false},
						   {true,true,true,true,false,true,true,true,true,true}};
		

		Maze2D myMaze = null;
		try {
			myMaze = new Maze2D();
		} catch (MazeInitializationException e) {
			e.printStackTrace();
		}
		
		boolean[][] myMazeLayout = myMaze.getMazeLayout();
		
		for (int row = 0; row<ref.length; row++) {
			for (int col = 0; col<ref[0].length; col++) {
				assert(myMazeLayout[row][col] == ref[row][col]);
			}
		}
	}
	
	/**
	 * Check if Maze2D handles empty layout
	 */
	@Test
	void testEmptyConfig() {
		try {
			new Maze2D("tests/empty.txt");
			fail("should have returned MazeInitializationException");
		} catch (MazeInitializationException e) {
			System.out.println(e.getMessage());
			assert(e.getClass() == EmptyFileException.class);
		}
	}
	
	/**
	 * Check if Maze2D handles incorrect maze layout
	 */
	@Test
	void testIncorrectConfig() {
		try {
			new Maze2D("tests/absurd_matrix_1.txt");
			fail("should have returned MazeInitializationException");
		} catch (MazeInitializationException e) {
			System.out.println(e.getMessage());
			assert(e.getClass() == DimensionInitializationException.class);
		}
		
		try {
			new Maze2D("tests/absurd_matrix_2.txt");
			fail("should have returned MazeInitializationException");
		} catch (MazeInitializationException e) {
			System.out.println(e.getMessage());
			assert(e.getClass() == DimensionInitializationException.class);
		}
		
		try {
			new Maze2D("tests/absurd_matrix_3.txt");
			fail("should have returned MazeInitializationException");
		} catch (MazeInitializationException e) {
			System.out.println(e.getMessage());
			assert(e.getClass() == DimensionInitializationException.class);
		}
		
		try {
			new Maze2D("tests/absurd_matrix_4.txt");
			fail("should have returned MazeInitializationException");
		} catch (MazeInitializationException e) {
			System.out.println(e.getMessage());
			assert(e.getClass() == DimensionInitializationException.class);
		}
	}
	
	/**
	 * Check if Maze2D handles maze layout with incorrect encoding 
	 */
	@Test
	void testBadEncodingConfig() {
		try {
			new Maze2D("tests/bad_encoding.txt");
			fail("should have returned MazeInitializationException");
		} catch (MazeInitializationException e) {
			System.out.println(e.getMessage());
			assert(e.getClass() == MazeEncodingException.class);
		}
	}
	
	/**
	 * Check if getMazeCell returns correct value of a cell
	 */
	@Test
	void testGetMazeCell() {
		Maze2D myMaze = null;
		try {
			myMaze = new Maze2D();
		} catch (MazeInitializationException e) {
			e.printStackTrace();
		}
		
		boolean[][] ref = {{true,false,false,false,false,false,false,false,false,false},
				   {true,true,true,true,true,false,false,true,true,false},
				   {false,false,false,false,true,false,false,false,true,false},
				   {false,true,true,true,true,true,true,true,true,false},
				   {false,true,false,false,false,false,false,true,false,false},
				   {false,true,true,true,true,true,false,true,false,true},
				   {false,false,false,false,false,false,false,true,false,true},
				   {true,true,false,true,true,true,true,true,true,true},
				   {false,true,false,true,false,true,false,false,false,false},
				   {true,true,true,true,false,true,true,true,true,true}};
		
		for (int row = 0; row<10; row++) {
			for (int col = 0; col<10; col++) {
				assert(ref[row][col] == myMaze.getMazeCell(row, col));
			}
		}
	}
	
	/**
	 * Test if changeCell update the cell accurately
	 */
	@Test
	void testChangeCell() {
		Maze2D myMaze = null;
		try {
			myMaze = new Maze2D();
		} catch (MazeInitializationException e) {
			e.printStackTrace();
		}
		
		boolean cell_before = myMaze.getMazeCell(0, 0);
		myMaze.changeCell(0, 0);
		boolean cell_after = myMaze.getMazeCell(0, 0);
		
		assert (cell_before != cell_after);
	}
}
