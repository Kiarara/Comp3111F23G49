package Shared;

import Function_B.ShortestPathFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class WindowTest extends SwingTestCase {

    private Window window;
    ShortestPathFinder finder;
    private LinkedList<int[]> path;

    @Before
    public void setUp() {
        window = new Window();
        window.set_maze("MazeMap_TnJ.csv");
        finder = new ShortestPathFinder(window.m);
        path = finder.findShortestPath(window.m.getEntry(),window.m.getExit());
    }

    @Test
    public void testSetMaze() {
        assertNotNull(window.getMaze());
    }

    @Test
    public void testDisplayMaze() {
        window.display_maze();
        // Add assertions to verify the display of the maze
        for (int i = 0; i<30; ++i){
            for (int j = 0; j<30; ++j)
            {
                if(window.m.maze[i][j] == 0)
                    assertEquals(1, window.Grid.get(i).get(j).getColor());
                else
                    assertEquals(0, window.Grid.get(i).get(j).getColor());
            }
        }
    }

    @Test
    public void testDisplayPath() {
        path = finder.findShortestPath(window.m.getEntry(),window.m.getExit());
        window.display_path(path); // target function

        // Add assertions to verify the display of the path
        for (int i = 0; i<30; ++i){
            for (int j = 0; j<30; ++j)
            {
                if(path.contains(new int[]{i, j}))
                    assertEquals(2, window.Grid.get(i).get(j).getColor());
            }
        }
    }

    @Test
    public void testRemoveExistingPath() {
        window.display_path(path);
        window.remove_existing_path(); // target function
        for (int i = 0; i<30; ++i){
            for (int j = 0; j<30; ++j)
            {
                assertNotEquals(2, window.Grid.get(i).get(j).getColor());
            }
        }
    }

    @Test
    public void testGameSetup() {
        JFrame frame = getTestFrame();
        JButton button = new JButton("Click to Start");

            // Simulate button click
            button.doClick();

            // Verify that the frame is disposed
            assertFalse(frame.isDisplayable());
        }

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