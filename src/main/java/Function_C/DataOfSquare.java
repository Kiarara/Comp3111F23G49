package Function_C;

import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;
import javax.swing.*;

public class DataOfSquare {

	
	//ArrayList that'll contain the colors
	ArrayList<Color> C =new ArrayList<>();
	ArrayList<JLabel> L = new ArrayList<>();
	int color; //2: snake , 1: food, 0:empty
	int image; //
	SquarePanel square;

	// constructor
	public DataOfSquare(int col) {
		
		//Let's add the color to the arrayList
		C.add(Color.darkGray);//0
		C.add(Color.BLUE);    //1
		C.add(Color.white);   //2
		color=col;
		image=0;
		square = new SquarePanel(C.get(color));

		// add the images of Tom and Jerry to the arrayList
		L.add(new JLabel());//0
		L.add(new JLabel(new ImageIcon("1_Tom.png")));//1
		L.add(new JLabel(new ImageIcon("2_Jerry.png")));//2

	}
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
	}

	public void changeObject(int l){
		image = l;
		square.setImage(L.get(l));
	}

	public void clearObject(){
		square.clearObject();
	}
}
