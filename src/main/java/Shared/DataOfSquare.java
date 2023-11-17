package Shared;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

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
		C.add(Color.blue);
		square = new SquarePanel(C.get(col));
		color = col;

		// Add the images of Tom and Jerry to the arrayList
		I.add(new ImageIcon("1_Tom-min.png"));//0
		I.add(new ImageIcon("2_Jerry-min.png"));//1
		I.add(new ImageIcon("3_freezer.png"));//2
		I.add(new ImageIcon("4_Nibbles.png"));//3

	}
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
		color = c;
	}

	public void changeObject(int i){
		square.setImage(I.get(i));
		obj = i;
	}

	public void clearObject(){
		square.clearObject();
		obj = -1;
	}

	public int getObject(){
		return obj;
	}
}
