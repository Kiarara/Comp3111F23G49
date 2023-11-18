package Shared;

import Function_C.VertexLocation;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MazeTest {
    private Maze maze;

    @Before
    public void setup() {
        maze = new Maze("MazeMap_TnJ.csv");
    }

    @Test
    public void testMazeCreation() {
        // assert entry and exit
        assertEquals(new VertexLocation(12, 0), maze.entry);
        assertEquals(new VertexLocation(1,29), maze.exit);

        // assert dimensions
        assertEquals(30, maze.maze.length);
        assertEquals(30, maze.maze[0].length);

    }

    @Test
    public void testGetExit() {
        assertNotNull(maze.getExit());
        int exit_x = maze.getExit().x;
        int exit_y = maze.getExit().y;
        assertEquals(1,exit_x);
        assertEquals(29, exit_y);
    }

    @Test
    public void testGetEntry() {
        assertNotNull(maze.getEntry());
        int exit_x = maze.getEntry().x;
        int exit_y = maze.getEntry().y;
        assertEquals(12,exit_x);
        assertEquals(0, exit_y);
    }
}
