package Shared;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The DataOfSquare class represents the data associated with a square in a grid
 * A square can have different colors(dark gray/white/blue) and different images(Tom/Jerry/Tuffy/freezer) displayed
 */
public class DataOfSquare {

	
	//ArrayList that'll contain the colors and the images to show
	ArrayList<Color> C =new ArrayList<>();
	ArrayList<ImageIcon> I = new ArrayList<>();
	SquarePanel square;
	int color;
	int obj;

	// constructor
	public DataOfSquare(int col) {
		
		// Add the colors to the arrayList
		C.add(Color.darkGray);//0 - dark gray representing wall
		C.add(Color.white);   //1 - white representing possible location
		C.add(Color.blue);	  //2 - blue used for path display
		square = new SquarePanel(C.get(col));
		color = col;
		obj = -1;

		// Add the images of Tom, Jerry, Tuffy and freezer to the arrayList
		I.add(new ImageIcon("1_Tom-min.png"));//0
		I.add(new ImageIcon("2_Jerry-min.png"));//1
		I.add(new ImageIcon("3_freezer.png"));//2
		I.add(new ImageIcon("4_Tuffy.png"));//3

	}

	// change the color of the square
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
		color = c;
	}

	// change the image displayed of the square
	public void changeObject(int i){
		square.setImage(I.get(i));
		obj = i;
	}

	// clear the image displayed of the square
	public void clearObject(){
		if(obj != -1){
			square.clearObject();
			obj = -1;
		}
	}

	// return the code for the object currently displayed
	public int getObject(){
		return obj;
	}

	// return the code for the color currently displayed
	public int getColor() { return color;}
}
