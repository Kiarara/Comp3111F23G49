package Shared;

import javax.swing.*;
import java.awt.*;

/**
 * The SquarePanel class represents a panel that displays a square with color and images.
 * It extends the JPanel class from the javax.swing package.
 */
public class SquarePanel extends JPanel{

	public SquarePanel(Color d){
		this.setBackground(d);
	}
	
	public void ChangeColor(Color d){
		this.setBackground(d);
		this.revalidate();
		this.repaint();
	}

	public void setImage(ImageIcon i) {
		// resize the image to fit the square
		Image scaled = i.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(scaled);
		this.add(new JLabel(icon));
		this.revalidate();
		this.repaint();
	}

	public void clearObject(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
}

