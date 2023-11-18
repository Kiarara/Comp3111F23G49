package Function_C;

import Shared.Window;

import javax.swing.*;

public class Main_C {

	public static void main(String[] args){

		//Creating the window with the grids

		Window f1= new Window();
		f1.gameSetup();

		//Setting up the window settings
		f1.setTitle("Escape from Tom");
		f1.setSize(900,900);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setLocationRelativeTo(null);

	}
}
