package Function_C;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 public class KeyboardListener extends KeyAdapter{
 	
 		public void keyPressed(KeyEvent e){
 		    switch(e.getKeyCode()){
		    	case 39:	// -> Right 
		    				//if it's not the opposite direction
		    				if(ThreadsController.directionJerry!=2)
		    					ThreadsController.directionJerry=1;
		    			  	break;
		    	case 38:	// -> Top
							if(ThreadsController.directionJerry!=4)
								ThreadsController.directionJerry=3;
		    				break;
		    				
		    	case 37: 	// -> Left 
							if(ThreadsController.directionJerry!=1)
								ThreadsController.directionJerry=2;
		    				break;
		    				
		    	case 40:	// -> Bottom
							if(ThreadsController.directionJerry!=3)
								ThreadsController.directionJerry=4;
		    				break;
		    	
		    	default: 	break;
 		    }
 		}
 	
 }
