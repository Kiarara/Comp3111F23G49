package Function_C;

import javax.swing.*;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		//Creating the window with all its awesome snaky features
		Window f1= new Window();
		
		//Setting up the window settings
		f1.setTitle("Escape from Tom");
		f1.setSize(300,300);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

	}
}
