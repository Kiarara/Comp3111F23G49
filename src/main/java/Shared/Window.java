package Shared;

import Function_C.KeyboardListener;
import Function_C.ThreadsController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Window extends JFrame{
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public static int width = 30;
	public static int height = 30;
	Maze m;

	public Window(boolean isGame){

		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<>();
		ArrayList<DataOfSquare> data;
		
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++){
			data= new ArrayList<>();
			for(int j=0;j<height;j++){
				DataOfSquare c = new DataOfSquare(1);
				data.add(c);
			}
			Grid.add(data);
		}
		
		// Setting up the layout of the panel
		getContentPane().setLayout(new GridLayout(30,30,0,0));
		
		// Start & pauses all threads, then adds every square of each thread to the panel
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				getContentPane().add(Grid.get(i).get(j).square);
			}
		}

		if (isGame)
		{
			// passing this value to the controller
			ThreadsController c = new ThreadsController(this);

			JFrame frame = new JFrame("Start");
			JButton button = new JButton("Click to Start");

			button.addActionListener(e -> {
				int result = JOptionPane.showConfirmDialog(frame, "Would you like to start the game?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					c.start();
					frame.dispose();
				}
			});

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(button, BorderLayout.CENTER);
			frame.setSize(300, 200);
			frame.setLocationRelativeTo(this);
			frame.setAlwaysOnTop(true);
			frame.setVisible(true);

			// Links the window to the keyboardlistenner.
			this.addKeyListener(new KeyboardListener());
		}
	}

	public void restart_game(){
		ThreadsController c = new ThreadsController(this);
		c.start();
	}

	public void set_maze(String csv_file){
		m = new Maze(csv_file);
	}

	public void display_maze(){
		for(int i=0;i<30;i++){
			for(int j=0;j<30;j++){
				Grid.get(i).get(j).lightMeUp(1);
				if (m.maze[i][j] == 1)
					Grid.get(i).get(j).lightMeUp(0);
			}
		}
	}

	public Maze getMaze(){
		return m;
	}
}
