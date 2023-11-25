package Shared;

import Function_B.ShortestPathFinder;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WindowTest {

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
    public void testWindow(){
        Window w = new Window(); // target function
        assertEquals(30, Window.height);
        assertEquals(30, Window.width);
        assertEquals(30, w.Grid.size());
        assertEquals(30, w.Grid.get(0).size());
    }

    @Test
    public void testSetMaze() {
        Window w = new Window();
        w.set_maze("MazeMap_TnJ.csv"); // target function
        assertNotNull(w.m);
        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            w.set_maze("fake_maze"); // target function
        });
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

        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            window.display_maze(); // target function
        });
    }

    @Test
    public void testGetMaze(){
        Maze m = window.getMaze(); // target function
        assertNotNull(m);
    }

    @Test
    public void testDisplayPath() throws InterruptedException {
        path = finder.findShortestPath(window.m.getEntry(),window.m.getExit());
        window.display_path(path); // target function

        sleep(1000);
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
    public void testDisplayPath_throw(){
        path = finder.findShortestPath(window.m.getEntry(),window.m.getExit());
        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            window.display_path(path); // target function
        });
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

        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            window.remove_existing_path(); // target function
        });
    }

    @Test
    public void testGameSetup() {
        window.gameSetup();
    }

    @Test
    public void testSetMode() {
        window.setMode();
    }

    @Test
    public void testStartGame() {
        window.start_game(1);
    }

}