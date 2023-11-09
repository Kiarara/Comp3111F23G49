package Function_C;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel{

	private Image image;
	private static final long serialVersionUID = 1L;

	public SquarePanel(Color d){
		this.setBackground(d);
		image = null;
	}
	
	public void ChangeColor(Color d){
		this.setBackground(d);
		this.repaint();
	}

	public void setImage(JLabel l) {
		this.add(l);
	}

	public void clearObject(){
		this.removeAll();
		this.revalidate();
		this.repaint();
	}

}

