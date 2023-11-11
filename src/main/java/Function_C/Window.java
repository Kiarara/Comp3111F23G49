package Function_C;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;


class Window extends JFrame{
	private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public static int width = 30;
	public static int height = 30;
	public Window() throws IOException {
		

		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;
		
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++){
			data= new ArrayList<DataOfSquare>();
			for(int j=0;j<height;j++){
				DataOfSquare c = new DataOfSquare(2);
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

		// passing this value to the controller
		ThreadsController c = new ThreadsController();
		//Let's start the game now...
		c.start();

		// Links the window to the keyboardlistenner.
		this.addKeyListener((KeyListener) new KeyboardListener());
		
	}
}
