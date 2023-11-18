package Shared;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MazeTest {
    @Test
    public void testMazeCreation() {
        Maze maze = new Maze("MazeMap_TnJ.csv");
        assertNotNull(maze.getEntry());
        assertNotNull(maze.getExit());
    }

    @Test
    public void testMazeDimensions() {
        Maze maze = new Maze("MazeMap_TnJ.csv");
        assertEquals(30, maze.maze.length);
        assertEquals(30, maze.maze[0].length);
    }

    @Test
    public void testGetExit() {
        Maze maze = new Maze("MazeMap_TnJ.csv");
        assertNotNull(maze.getExit());
    }

    @Test
    public void testGetEntry() {
        Maze maze = new Maze("MazeMap_TnJ.csv");
        assertNotNull(maze.getEntry());
    }
}
