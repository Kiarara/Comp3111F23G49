package Shared;

import Function_C.GameStartButton;
import Function_C.KeyboardListener;
import Function_C.ModeButton;
import Function_C.ThreadsController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Thread.sleep;


/**
 * The Window class represents the main window of the maze game.
 * It extends the JFrame class from the javax.swing package.
 *
 * @author PENG Xinyin(Kiara)
 */
public class Window extends JFrame{

	/**
	 * the array list that eventually contains all the squares
	 */
	public static ArrayList<ArrayList<DataOfSquare>> Grid;

	/**
	 * the width of the window
	 */
	public static int width = 30;

	/**
	 * the height of the window
	 */
	public static int height = 30;

	/**
	 * the maze to be displayed
	 */
	Maze m;


	/**
	 * Constructs a Window object.
	 * Initializes the grid and sets up the layout of the panel.
	 * Adds each square in the grid to the panel.
	 */
	public Window(){

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
	}

	/**
	 * Reads a maze from a CSV file and sets it as the maze to be displayed.
	 *
	 * @param csv_file The path of the CSV file containing the maze data.
	 */
	public void set_maze(String csv_file) {
		try {
			m = new Maze(csv_file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Displays the maze recorded by the window.
	 * Iterates over the maze and updates the color of each square in the grid based on the corresponding value in the maze matrix.
	 */
	public void display_maze() {
		for(int i=0;i<30;i++){
			for(int j=0;j<30;j++){
				if (m.maze[i][j] == 1)
					Grid.get(i).get(j).lightMeUp(0);
				else
					Grid.get(i).get(j).lightMeUp(1);
				try {
					sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * Displays a specific path in the maze on the window.
	 *
	 * @param path The path to be displayed, represented as a linked list of coordinates.
	 */
	public void display_path(LinkedList<int[]> path){
		for (int[] loc : path) {
			Grid.get(loc[0]).get(loc[1]).lightMeUp(2);
			try { sleep(1);}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Clears the path displayed in the maze.
	 * Reverts the color of the corresponding squares in the grid.
	 */
	public void remove_existing_path(){
		for(int i=0;i<30;i++){
			for(int j=0;j<30;j++){
				if (Grid.get(i).get(j).color == 2)
					Grid.get(i).get(j).lightMeUp(1);
				try { sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * Fetches the maze held by the window.
	 *
	 * @return The maze object representing the maze held by the window.
	 */
	public Maze getMaze(){
		return m;
	}

	/**
	 * Prompts the user to start the game.
	 * Displays a JFrame with a start button and sets up the game when the button is clicked.
	 */
	public void gameSetup(){
		JFrame frame = new JFrame("Welcome to the game!");
		GameStartButton start_button = new GameStartButton(this, frame,true);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(start_button, BorderLayout.CENTER);
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(this);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);

		// Links the window to the keyboard listener.
		this.addKeyListener(new KeyboardListener());
	}

	/**
	 * Prompts the user to start the game.
	 * Displays a JFrame with a start button and sets up the game when the button is clicked.
	 */
	public void setMode(){
		JFrame mode_selection = new JFrame("Select your game difficulty");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.CENTER;

		JPanel optionPanel = new JPanel();
		optionPanel.add(new ModeButton(0, mode_selection,this));
		optionPanel.add(new ModeButton(1, mode_selection, this));
		optionPanel.add(new ModeButton(2, mode_selection, this));

		mode_selection.getContentPane().setLayout(new GridBagLayout());
		mode_selection.getContentPane().add(optionPanel, gbc);
		mode_selection.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mode_selection.setSize(300, 200);
		mode_selection.setLocationRelativeTo(this);
		mode_selection.setAlwaysOnTop(true);
		mode_selection.setVisible(true);
	}

	/**
	 * Starts the game with the specified difficulty mode.
	 * Creates a ThreadsController object and calls its start() method.
	 *
	 * @param mode The difficulty mode of the game.
	 */
	public void start_game(int mode){
		ThreadsController c = new ThreadsController(this);
		c.setMode(mode);
		c.start();
	}

}
