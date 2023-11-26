package Function_B;

import Shared.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;

class ShortestPathFinderTest {
    @Test
    void ShortestPathFinder() throws IOException {
        Maze m = new Maze("MazeMap_TnJ.csv");
        ShortestPathFinder finder = new ShortestPathFinder(m);
        // check if the shortest path has been successfully initiated
        assertEquals(finder.shortestpath.size(),0);
    }



    @Test
    void findShortestPath() throws IOException {
        // Test on MazeMap_TnJ.csv
        // Length for the shortest path should be 49
        Maze m = new Maze("MazeMap_TnJ.csv");
        ShortestPathFinder finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(), m.getExit());
        int path_length = 0;
        if (finder.shortestpath != null) {
            LinkedList<int[]> path = finder.findShortestPath(m.getEntry(), m.getExit());
            path_length = path.size();
        }
        assertEquals(path_length, 49);

        // Also test on ImpossibleMap.csv, which should return "No path found"
        m = new Maze("ImpossibleMap.csv");
        finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(), m.getExit());
        path_length = 0;
        if (finder.shortestpath != null) {
            LinkedList<int[]> path = finder.findShortestPath(m.getEntry(), m.getExit());
            path_length = path.size();
        }
        assertEquals(path_length, 0);

    }


    @Test
    void bfs() throws IOException {
        // as bfs() will update the distance and visited variables in the class, we will use that to test

        // test on the MazeMap_TnJ.csv
        // see if the map[end_row][end_column] is visited, and the distance equals to the shortest path
        Maze m = new Maze("MazeMap_TnJ.csv");
        ShortestPathFinder finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(), m.getExit());
        assertEquals(finder.visited[m.getExit().x][m.getExit().y], 1);
        assertEquals(finder.distance[m.getExit().x][m.getExit().y], 48); // Note that when the length is 49, the distance from the beginning to the end is 48

        // Also test on ImpossibleMap.csv, which shouldn't reach the exit
        m = new Maze("ImpossibleMap.csv");
        finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(), m.getExit());
        assertEquals(finder.visited[m.getExit().x][m.getExit().y], 0);
        assertEquals(finder.distance[m.getExit().x][m.getExit().y], 0);
    }



    @Test
    void find_next() throws IOException {
        // we will use MazeMap_TnJ to test if the find_next() is providing the correct direction
        // as we know, the exit for the map is [1][29], and the previous path should be [1][28]
        Maze m = new Maze("MazeMap_TnJ.csv");
        ShortestPathFinder finder = new ShortestPathFinder(m);
        int[] next = finder.find_next(m.getEntry(), m.getExit());
        assertEquals(next[0], 1);
        assertEquals(next[1], 28);
    }



    @Test
    void isValidMove() throws IOException{
        // Will test this function on "ImpossibleMap.csv"
        // The map is separated on column 13
        Maze m = new Maze("ImpossibleMap.csv");
        ShortestPathFinder finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(),m.getExit());

        // Check if it can detect out-of-bound row input
        assertFalse(finder.isValidMove(-1,1));
        assertFalse(finder.isValidMove(32,1));

        // Check if it can detect out-of-bound column input
        assertFalse(finder.isValidMove(1,-1));
        assertFalse(finder.isValidMove(1,32));

        // Check if it can detect barriers on the path
        // Choose [1,1] and [11,1]
        assertFalse(finder.isValidMove(1,1));
        assertFalse(finder.isValidMove(11,1));

        // The clear vertexes which can be reached from the entrance in column 0-12 should have been visited, so should return false
        // Choose [12,2] and [13,4]
        assertFalse(finder.isValidMove(12,1));
        assertFalse(finder.isValidMove(13,4));


        // All the clear vertexes in column 14-29 shouldn't been visited (because they are blocked from the entrace)
        // so should return true
        // Choose [1,28] and [2,26]
        assertTrue(finder.isValidMove(1,28));
        assertTrue(finder.isValidMove(2,26));
    }



    @Test
    void resetState() throws IOException {
        Maze m = new Maze("MazeMap_TnJ.csv");
        ShortestPathFinder finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(),m.getExit());
        finder.resetState();
        // The function will initialize visited, distance, prevCol and prevRow, and we have a check
        byte [][] ByteZeros = new byte[30][30];
        int [][] IntZeros = new int[30][30];
        assertArrayEquals(finder.visited,ByteZeros);
        assertArrayEquals(finder.distance,ByteZeros);
        assertArrayEquals(finder.prevCol,IntZeros);
        assertArrayEquals(finder.prevRow,IntZeros);
    }

    @Test
    public void saveMazeToFile() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Maze m = new Maze("MazeMap_TnJ.csv");
        ShortestPathFinder finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(),m.getExit());
        finder.saveMazeToFile();

        String expectedOutput = "Successfully save the file. ";
        assertEquals(expectedOutput, outContent.toString());
        assertThrows(RuntimeException.class, () -> {
            finder.test = true;
            finder.saveMazeToFile(); // target function
        });
    }

}
