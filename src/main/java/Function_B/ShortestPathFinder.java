package Function_B;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Function_C.ThreadsController;
import Function_C.VertexLocation;
import Shared.*;
/**
 * The ShortestPathFinder class represents a class to find the shortest path in a map
 * It extends the JFrame class from the javax.swing package.
 *
 * @author LIU Muyuan(Oakley)
 */
public class ShortestPathFinder {
    /**
     * The array of the data
     */
    public int[][] map;
    /**
     * The row number of the map
     */
    public int numRows = 30;
    /**
     * The column number of the map
     */
    public int numCols = 30;
    /**
     * The array which stores whether a vertex has been visited or not
     */
    public byte[][] visited;
    /**
     * The array which stores the distance from entry to this vertex
     */
    public byte[][] distance;
    /**
     * The array which stores the row index of the previous vertex when we run bfs on the map
     */
    public int[][] prevRow;
    /**
     * The array which stores the column index of the previous vertex when we run bfs on the map
     */
    public int[][] prevCol;
    /**
     * The linked list which stored the shortest path data in the format of [row, column]
     */
    LinkedList<int[]> shortestpath;
    /**
     * The queue for running bfs algorithm
     */
    Queue<int[]> queue;

    /**
     * Explicit constructor for the class; initialize all the class members
     *
     * @param m                 The maze for this round of game
     */
    public ShortestPathFinder(Maze m) {
        map = m.maze;
        visited = new byte[numRows][numCols];
        distance = new byte[numRows][numCols];
        prevRow = new int[numRows][numCols];
        prevCol = new int[numRows][numCols];

        shortestpath = new LinkedList<>();
        queue = new LinkedList<>();
    }

    /**
     * The method constructs the shortest path between two given VertexLocation
     *
     * @param a                 The start of the path
     * @param b                 The end of the path
     * @return                  A linkedlist representing the shortest path find
     */
    public LinkedList<int[]> findShortestPath(VertexLocation a, VertexLocation b) {
        int startRow = a.x;
        int startCol = a.y;
        int endRow = b.x;
        int endCol = b.y;
        shortestpath.clear();
        resetState();
        bfs(startRow, startCol, endRow, endCol);

        if (visited[endRow][endCol] == 0) {
            System.out.println("No path found.");
            shortestpath = null;
        }
        else {

            int row = endRow;
            int col = endCol;

            while (row != startRow || col != startCol) {
                shortestpath.addFirst(new int[]{row, col});
                int preRow = prevRow[row][col];
                int preCol = prevCol[row][col];
                row = preRow;
                col = preCol;
            }
            shortestpath.addFirst(new int[]{startRow, startCol});
        }
        return shortestpath;
    }

    /**
     * The method runs breadth-first search on the map
     * It is called in {@link ShortestPathFinder#findShortestPath(VertexLocation, VertexLocation)} to update the class members
     *
     * @param startRow                  The row index of the start point
     * @param startCol                  The column index of the start point
     * @param endRow                    The row index of the end point
     * @param endCol                    The column index of the end point
     *
     */
    private void bfs(int startRow, int startCol, int endRow, int endCol) {
        queue.clear();
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = 1;
        distance[startRow][startCol] = 0;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] vertex = queue.poll();
            int row = vertex[0];
            int col = vertex[1];

            if (row == endRow && col == endCol) {
                return;
            }

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValidMove(newRow, newCol)) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = 1;
                    distance[newRow][newCol] = (byte) (distance[row][col] + 1);
                    prevRow[newRow][newCol] = row;
                    prevCol[newRow][newCol] = col;
                }
            }
        }
    }

    /**
     * The method helps Tom to find out the next move on the shortest path to Jerry
     * It is used in class {@link ThreadsController}
     *
     * @param a                 The start of the path, which is the location of Jerry
     * @param b                 The end of the path, which is the location of Tom
     * @return                  The second-last location on the path, which determines the move of Tom
     */
    public int[] find_next(VertexLocation a, VertexLocation b){
        findShortestPath(a, b);
        int[] next = new int[2];
        next[0] = shortestpath.get(shortestpath.size()-2)[0];
        next[1] = shortestpath.get(shortestpath.size()-2)[1];
        return next;
    }

    /**
     * The method helps {@link ShortestPathFinder#bfs} to detect whether a vertex should be added to the queue
     *
     * @param row                 The row index of the vertex
     * @param col                 The column index of the vertex
     * @return                    Whether the move is valid
     */
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && map[row][col] == 0 && visited[row][col] == 0;
    }

    /**
     * The method is used in {@link ShortestPathFinder#findShortestPath(VertexLocation, VertexLocation)} to reset the class members of the class {@link ShortestPathFinder}
     *
     */
    public void resetState() {
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(visited[i], (byte) 0);
            Arrays.fill(distance[i], (byte) 0);
            Arrays.fill(prevRow[i], 0);
            Arrays.fill(prevCol[i], 0);
        }
    }

    /**
     * The method will generate a csv file named "FunctionB_output.csv" for reference
     *
     */
    public void saveMazeToFile() {
        try {
            FileWriter writer = new FileWriter("FunctionB_output.csv");
            for (int i = 0; i < shortestpath.size(); i++) {
                writer.append("[");
                writer.append(String.valueOf(shortestpath.get(i)[0]));
                if (Integer.toString(shortestpath.get(i)[0]).length() == 1) writer.append(" ");
                writer.append(",");
                if (Integer.toString(shortestpath.get(i)[1]).length() == 1) writer.append(" ");
                writer.append(String.valueOf(shortestpath.get(i)[1]));
                writer.append("]");
                if (i % 8 == 7 ) {
                    writer.append("\n");
                }
                else if (i < shortestpath.size()-1) {
                    writer.append(",\t");
                }
            }
            System.out.print("Successfully save the file. ");
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.out.println("Error occurred during saving the maze to a file.");
            e.printStackTrace();
        }
    }

}