package Function_A;
import Shared.Window;

import javax.swing.*;

public class Main_A {
    public static void main(String[] args) {

        Board_MST Board = new Board_MST();
        //Board.build_maze();
        Board.build_maze_with_single_wall();
        for(int i=0;i<20;i++){
            Board.build_more_path();
        }
        //Board.wall_to_maze();
        Board.saveMazeToFile();

        //Creating the window with the grids
        Window f1= new Window();

        //Setting up the window settings
        f1.setTitle("Maze generated");
        f1.setSize(900,800);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f1.setLocationRelativeTo(null);

        f1.set_maze("actual_maze.csv");
        f1.display_maze();
    }
}
