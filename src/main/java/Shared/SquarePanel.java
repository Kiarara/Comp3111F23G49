package Shared;

import javax.swing.*;
import java.awt.*;

/**
 * The SquarePanel class represents a panel that displays a square with color and images.
 * It extends the JPanel class from the javax.swing package.
 *
 * @author PENG Xinyin(Kiara)
 */
public class SquarePanel extends JPanel{

	/**
	 * Constructs a SquarePanel with the specified background color.
	 *
	 * @param d The background color of the panel.
	 */
	public SquarePanel(Color d){
		this.setBackground(d);
	}


	/**
	 * Changes the background color of the panel.
	 *
	 * @param d The new background color.
	 */
	public void ChangeColor(Color d){
		this.setBackground(d);
		this.revalidate();
		this.repaint();
	}

	/**
	 * Sets the image to be displayed in the panel.
	 * The image is resized to fit the square.
	 *
	 * @param i The ImageIcon representing the image.
	 */
	public void setImage(ImageIcon i) {
		// resize the image to fit the square
		Image scaled = i.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(scaled);
		this.add(new JLabel(icon));
		this.revalidate();
		this.repaint();
	}

	/**
	 * Clears the panel by removing all components.
	 */
	public void clearObject(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
}

