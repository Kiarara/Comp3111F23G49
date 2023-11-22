package Function_C;

import javax.swing.*;

/**
 * The ExitButton implements a button for users to exit the entire game
 * Note: Clicking on the button, the entire process will be terminated
 */
public class ExitButton extends JButton {
    public ExitButton(){
        setText("Exit");
        addActionListener(e -> System.exit(0));
    }
}
