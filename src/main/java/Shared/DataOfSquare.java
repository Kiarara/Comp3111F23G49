package Shared;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The DataOfSquare class represents the data associated with a square in a grid
 * A square can have different colors(dark gray/white/blue) and different images(Tom/Jerry/Tuffy/freezer) displayed
 *
 * @author PENG Xinyin(Kiara)
 */
public class DataOfSquare {


	/**
	 * ArrayList that'll contain the colors to show
	 */
	ArrayList<Color> C =new ArrayList<>();

	/**
	 * ArrayList that'll contain the images to show
	 */
	ArrayList<ImageIcon> I = new ArrayList<>();

	/**
	 * The actual square to modify
	 */
	SquarePanel square;

	/**
	 * The current color of the square
	 */
	int color;

	/**
	 * The current object of the square
	 */
	int obj;

	/**
	 * Constructs a DataOfSquare object with the specified color.
	 *
	 * @param col The code representing the color of the square (0 for dark gray, 1 for white, 2 for blue).
	 */
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

	/**
	 * Changes the color of the square.
	 *
	 * @param c The code representing the new color of the square.
	 */
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
		color = c;
	}

	/**
	 * Changes the image displayed on the square.
	 *
	 * @param i The code representing the new image to be displayed on the square.
	 */
	public void changeObject(int i){
		square.setImage(I.get(i));
		obj = i;
	}

	/**
	 * Clears the image displayed on the square.
	 * If no image is currently displayed, this method has no effect.
	 */
	public void clearObject(){
		if(obj != -1){
			square.clearObject();
			obj = -1;
		}
	}

	/**
	 * Returns the object currently displayed on the square.
	 *
	 * @return The code representing the object displayed.
	 */
	public int getObject(){return obj;}

	/**
	 * Returns the code representing the color of the square currently.
	 *
	 * @return The code representing the color.
	 */
	public int getColor() { return color;}
}
