package Function_C;

import Shared.Window;

import javax.swing.*;

/**
 * The Main_C is for starting the Tom and Jerry Game
 *
 * @author PENG Xinyin(Kiara)
 */
public class Main_C {

	/**
	 * The entry point of the program.
	 *
	 * @param args The command line arguments.
	 */
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
