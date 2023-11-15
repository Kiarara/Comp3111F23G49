package Function_C;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public SquarePanel(Color d){
		this.setBackground(d);
	}
	
	public void ChangeColor(Color d){
		this.setBackground(d);
		this.revalidate();
		this.repaint();
	}

	public void setImage(ImageIcon i) {
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

