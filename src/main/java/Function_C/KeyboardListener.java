package Function_C;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 public class KeyboardListener extends KeyAdapter{
 	
 		public void keyPressed(KeyEvent e){
 		    switch(e.getKeyCode()){
		    	case 39:
					ThreadsController.directionJerry=1;
					break;
		    	case 38:
					ThreadsController.directionJerry=3;
					break;
		    	case 37:
					ThreadsController.directionJerry=2;
					break;
		    	case 40:	// -> Bottom
					ThreadsController.directionJerry=4;
					break;
		    	default: 	break;
 		    }
 		}
 	
 }
