package Shared;

import Function_C.VertexLocation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Maze class represents a maze with an entry and an exit point
 *
 * @author PENG Xinyin(Kiara)
 */
public class Maze {

    VertexLocation entry;
    VertexLocation exit;

    public int[][] maze; // the maze represented in 2D integer array

    /**
     * Constructs a Maze object by reading a maze from a CSV file.
     *
     * @param csv_file The path to the CSV file containing the maze data.
     * @throws IOException If an I/O error occurs while reading the CSV file.
     */
    public Maze(String csv_file) throws IOException {
        // reading a maze from the csv file specified
        BufferedReader br = new BufferedReader(new FileReader(csv_file));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        // interpret the maze read
        int numRows = lines.size();
        int numCols = lines.get(0).split(",").length;
        maze = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] elements = lines.get(i).split(",");
            for (int j = 0; j < numCols; j++) {
                maze[i][j] = Integer.parseInt(elements[j].trim());
            }
        }

        // find entry and exit
        for (int i = 0; i < 30; i++){
            for (int j = 0; j <30; j++){
                if (j == 0 && maze[i][j] == 0) entry = new VertexLocation(i, j);
                if (j == 29 && maze[i][j] == 0) exit = new VertexLocation(i,j);
            }
        }
    }

    /**
     * Returns the location of the exit point of the maze.
     *
     * @return The VertexLocation representing the exit point.
     */
    public VertexLocation getExit(){
        return exit;
    }

    /**
     * Returns the location of the entry point of the maze.
     *
     * @return The VertexLocation representing the entry point.
     */
    public VertexLocation getEntry(){
        return entry;
    }
}
