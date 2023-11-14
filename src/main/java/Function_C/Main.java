package Function_C;

import javax.swing.*;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		//Creating the window with the grids
		Window f1= new Window();
		
		//Setting up the window settings
		f1.setTitle("Escape from Tom");
		f1.setSize(900,900);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setLocationRelativeTo(null);


	}
}
