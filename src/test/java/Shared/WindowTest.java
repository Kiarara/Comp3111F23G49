package Shared;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;

public class WindowTest {

    private Window window;

    @Before
    public void setUp() {
        window = new Window();
    }

    @Test
    public void testSetMaze() {
        window.set_maze("actual_maze.csv"); // Assuming "test_maze.csv" is a valid maze file
        assertNotNull(window.getMaze());
        assert
    }

    @Test
    public void testDisplayMaze() {
        window.set_maze("actual_maze.csv"); // Assuming "test_maze.csv" is a valid maze file
        window.display_maze();
        // Add assertions to verify the display of the maze
    }

    @Test
    public void testDisplayPath() {
        LinkedList<int[]> path = new LinkedList<>();
        // Add elements to the path
        window.display_path(path);
        // Add assertions to verify the display of the path
    }

    @Test
    public void testRemoveExistingPath() {
        window.remove_existing_path();
        // Add assertions to verify the removal of the existing path
    }

    @Test
    public void testGameSetup() {
        window.gameSetup();
        // Add assertions to verify the game setup
    }

    @Test
    public void testSetMode() {
        window.setMode();
        // Add assertions to verify the mode selection
    }

    @Test
    public void testStartGame() {
        window.start_game(1); // Assuming 1 represents a specific game mode
        // Add assertions to verify the start of the game
    }

}