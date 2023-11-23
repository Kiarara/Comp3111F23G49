package Function_C;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The KeyboardListener Class is for fetching the keyboard input to modify Jerry's moving direction
 *
 * @author PENG Xinyin(Kiara)
 */
 public class KeyboardListener extends KeyAdapter{

	/**
	 * Invoked when a key is pressed.
	 *
	 * @param e The KeyEvent object representing the key event.
	 */
	 public void keyPressed(KeyEvent e){
		 switch(e.getKeyCode()){
			 case 39: // -> Right
				 ThreadsController.directionJerry=1;
				 break;
			 case 38: // -> Bottom
				 ThreadsController.directionJerry=3;
				 break;
			 case 37: // -> Left
				 ThreadsController.directionJerry=2;
				 break;
			 case 40:	// -> Top
				 ThreadsController.directionJerry=4;
				 break;
			 default:
				 break;
		 }
	 }
 	
 }
