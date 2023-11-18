package Function_B;

import Function_A.Board_MST;
import Shared.*;

import javax.swing.*;
import java.util.LinkedList;

public class Main_B {
    public static void main(String[] args) {
        Board_MST Board = new Board_MST();
        Board.build_maze();
        Board.saveMazeToFile();

        //Creating the window with the grids
        Window f1= new Window();

        //Setting up the window settings
        f1.setTitle("Shortest path");
        f1.setSize(900,900);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);

        f1.set_maze("actual_maze.csv"); // "actual_maze.csv"
        f1.display_maze();

        Maze m = f1.getMaze();
        ShortestPathFinder finder = new ShortestPathFinder(m);
        finder.findShortestPath(m.getEntry(), m.getExit());
        if (finder.shortestpath != null) finder.displayPath(f1);
        LinkedList<int[]> path = finder.findShortestPath(m.getEntry(), m.getExit());
        f1.display_path(path);
    }
}
