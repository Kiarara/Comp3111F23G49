package Function_C;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class DataOfSquare {

	
	//ArrayList that'll contain the colors
	ArrayList<Color> C =new ArrayList<>();
	ArrayList<ImageIcon> I = new ArrayList<>();
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
		I.add(new ImageIcon());//0
		I.add(new ImageIcon("1_Tom-min.png"));//1
		I.add(new ImageIcon("2_Jerry-min.png"));//2

	}
	public void lightMeUp(int c){
		square.ChangeColor(C.get(c));
	}

	public void changeObject(int i){
		image = i;
		square.setImage(I.get(i));
	}

	public void clearObject(){
		square.clearObject();
	}
}
