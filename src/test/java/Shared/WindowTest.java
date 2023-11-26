package Shared;

import Function_B.ShortestPathFinder;

import org.junit.jupiter.api.*;

import java.util.LinkedList;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WindowTest {

    Window window;
    ShortestPathFinder finder;
    LinkedList<int[]> path;


    @BeforeEach
    public void setUp() {
        window = new Window();
        window.set_maze("MazeMap_TnJ.csv");
        finder = new ShortestPathFinder(window.m);
        path = finder.findShortestPath(window.m.getEntry(),window.m.getExit());
    }

    @Test
    @Order(1)
    public void testWindow(){
        Window w = new Window(); // target function
        Assertions.assertEquals(30, Window.height);
        Assertions.assertEquals(30, Window.width);
        Assertions.assertEquals(30, w.Grid.size());
        Assertions.assertEquals(30, w.Grid.get(0).size());
    }

    @Test
    @Order(2)
    public void testSetMaze() {
        Window w = new Window();
        w.set_maze("MazeMap_TnJ.csv"); // target function
        Assertions.assertNotNull(w.m);
        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            w.set_maze("fake_maze"); // target function
        });
    }

    @Test
    @Order(3)
    public void testDisplayMaze() {
        window.display_maze(); // target function
        // Add assertions to verify the display of the maze
        for (int i = 0; i<30; ++i){
            for (int j = 0; j<30; ++j)
            {
                if(window.m.maze[i][j] == 0)
                    Assertions.assertEquals(1, window.Grid.get(i).get(j).getColor());
                else
                    Assertions.assertEquals(0, window.Grid.get(i).get(j).getColor());
            }
        }
    }

    @Test
    @Order(4)
    public void testDisplayMaze_throw(){
        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            window.display_maze(); // target function
        });
    }


    @Test
    @Order(5)
    public void testGetMaze(){
        Maze m = window.getMaze(); // target function
        Assertions.assertNotNull(m);
    }

    @Test
    @Order(6)
    public void testDisplayPath() throws InterruptedException {
        path = finder.findShortestPath(window.m.getEntry(),window.m.getExit());
        window.display_path(path); // target function

        sleep(1000);
        // Add assertions to verify the display of the path
        for (int i = 0; i<30; ++i){
            for (int j = 0; j<30; ++j)
            {
                if(path.contains(new int[]{i, j}))
                    Assertions.assertEquals(2, window.Grid.get(i).get(j).getColor());
            }
        }
    }

    @Test
    @Order(7)
    public void testDisplayPath_throw(){
        path = finder.findShortestPath(window.m.getEntry(),window.m.getExit());
        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            window.display_path(path); // target function
        });
    }

    @Test
    @Order(8)
    public void testRemoveExistingPath() {
        window.display_path(path);
        window.remove_existing_path(); // target function
        for (int i = 0; i<30; ++i){
            for (int j = 0; j<30; ++j)
            {
                Assertions.assertNotEquals(2, window.Grid.get(i).get(j).getColor());
            }
        }

        assertThrows(RuntimeException.class, () -> {
            Thread.currentThread().interrupt();  // Force an InterruptedException to be thrown
            window.remove_existing_path(); // target function
        });
    }

    @Test
    @Order(9)
    public void testGameSetup() {
        window.gameSetup();
    }

    @Test
    @Order(10)
    public void testSetMode() {
        window.setMode();
    }

    @Test
    @Order(11)
    public void testStartGame() {
        Window w = new Window();
        w.start_game(1); // target function
    }
}