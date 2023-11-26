package Function_B;

import Function_A.Board_MST;
import Shared.*;
import javax.swing.*;
import java.util.LinkedList;
/**
 * The Main_B is for generating a map and find the shortest path between entry and exit
 *
 * @author LIU Muyuan (Oakley)
 */
public class Main_B {
    /**
     * The entry point of the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Board_MST Board = new Board_MST();
        Board.build_maze_with_single_wall();
        for(int i=0;i<20;i++){
            Board.build_more_path();
        }

        Board.saveMazeToFile();

        //Creating the window with the grids
        Window f1= new Window();

        //Setting up the window settings
        f1.setTitle("Maze generated with shortest path");
        f1.setSize(900,800);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setLocationRelativeTo(null);

        f1.set_maze("actual_maze.csv");
        f1.display_maze();

        Maze m = f1.getMaze();
        ShortestPathFinder finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(), m.getExit());
        finder.saveMazeToFile();

        if (finder.shortestpath != null) {
            LinkedList<int[]> path = finder.findShortestPath(m.getEntry(), m.getExit());
            f1.display_path(path);
            System.out.print("Shortest path found!");
        }


    }
}
