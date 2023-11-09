package Function_C;

import java.awt.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DataOfSquare {

	
	//ArrayList that'll contain the colors
	ArrayList<Color> C =new ArrayList<Color>();
	ArrayList<JLabel> L = new ArrayList<>();
	int color; //2: snake , 1: food, 0:empty
	int image;
	SquarePanel square;

	// constructor
	public DataOfSquare(int col) throws IOException {
		
		//Let's add the color to the arrayList
		C.add(Color.darkGray);//0
		C.add(Color.BLUE);    //1
		C.add(Color.white);   //2
		color=col;
		square = new SquarePanel(C.get(color));

		// add the images of Tom and Jerry to the arrayList
		L.add(new JLabel(new ImageIcon("1_Tom.png")));//0
		L.add(new JLabel(new ImageIcon("2_Jerry.png")));//1

	}
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
	}

	public void changeObject(int l){
		square.setImage(L.get(l));
	}

	public void clearObject(){
		square.clearObject();
	}
}
