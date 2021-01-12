package greco.insidezecube.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import greco.insidezecube.exception.MazeInitializationException;
import greco.insidezecube.maze.Main;
import greco.insidezecube.maze.Maze2D;
import greco.insidezecube.utils.Pair;

class MainTest {

	@Test
	void testGetMazeDimension() {
		Main.instantiateDefaultMaze();
		Pair<Integer,Integer> dimensionRef = new Pair<Integer,Integer>(10,10);
		Pair<Integer,Integer> dimension = Main.getMazeDimension();
		assert (dimensionRef.equals(dimension));
		
		Main.instantiateMaze("tests/default2.txt");
		dimensionRef = new Pair<Integer,Integer>(7,10);
		dimension = Main.getMazeDimension();
		assert (dimensionRef.equals(dimension));
	}

}
