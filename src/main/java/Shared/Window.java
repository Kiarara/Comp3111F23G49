package Shared;

import Function_C.KeyboardListener;
import Function_C.ThreadsController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


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
			JFrame frame = new JFrame("Welcome to the game!");
			JButton button = new JButton("Click to Start");

			button.addActionListener(e -> {
					frame.dispose();
					setMode();
					this.setVisible(true);
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

	private void setMode(){
		ThreadsController c = new ThreadsController(this);

		JFrame mode_selection = new JFrame("Select your game difficulty");
		JButton easy = new JButton("Easy");
		JButton medium = new JButton("Medium");
		JButton hard = new JButton("hard");

		easy.addActionListener(f-> {
			c.setMode(0);
			c.start();
			mode_selection.dispose();
		});

		medium.addActionListener(f-> {
			c.setMode(1);
			c.start();
			mode_selection.dispose();
		});

		hard.addActionListener(f-> {
			c.setMode(2);
			c.start();
			mode_selection.dispose();
		});

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.CENTER;

		JPanel optionPanel = new JPanel();
		optionPanel.add(easy);
		optionPanel.add(medium);
		optionPanel.add(hard);

		mode_selection.getContentPane().setLayout(new GridBagLayout());
		mode_selection.getContentPane().add(optionPanel, gbc);
		mode_selection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mode_selection.setSize(300, 200);
		mode_selection.setLocationRelativeTo(this);
		mode_selection.setAlwaysOnTop(true);
		mode_selection.setVisible(true);
	}

	public void restart_game(){
		setMode();
	}

	public void set_maze(String csv_file){
		m = new Maze(csv_file);
	}

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

	public Maze getMaze(){
		return m;
	}
}
