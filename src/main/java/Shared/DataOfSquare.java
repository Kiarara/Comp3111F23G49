package Shared;

import Shared.SquarePanel;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class DataOfSquare {

	
	//ArrayList that'll contain the colors and the images to show
	ArrayList<Color> C =new ArrayList<>();
	ArrayList<ImageIcon> I = new ArrayList<>();
	SquarePanel square;

	// constructor
	public DataOfSquare(int col) {
		
		// Add the colors to the arrayList
		C.add(Color.darkGray);//0 - dark gray representing wall
		C.add(Color.white);   //1 - white representing possible location
		C.add(Color.blue);
		square = new SquarePanel(C.get(col));


		// Add the images of Tom and Jerry to the arrayList
		I.add(new ImageIcon("1_Tom-min.png"));//0
		I.add(new ImageIcon("2_Jerry-min.png"));//1

	}
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
	}

	public void changeObject(int i){
		square.setImage(I.get(i));
	}

	public void clearObject(){
		square.clearObject();
	}
}
