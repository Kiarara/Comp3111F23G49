package Function_A;
import Shared.Window;

import javax.swing.*;

public class Main_A {
    public static void main(String[] args){

        Board_MST Board = new Board_MST();
        Board.build_maze();
        Board.saveMazeToFile();

        //Creating the window with the grids
        Window f1= new Window(false);

        //Setting up the window settings
        f1.setTitle("Maze generated");
        f1.setSize(900,900);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);

        f1.set_maze("actual_maze.csv");
        f1.display_maze();
    }
}
