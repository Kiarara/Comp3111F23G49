package Function_C;

import javax.swing.*;

/**
 * The ExitButton implements a button for users to exit the entire game
 * Note: Clicking on the button, the entire process will be terminated
 *
 * @author PENG Xinyin(Kiara)
 */
public class ExitButton extends JButton {

    /**
     * Constructs an ExitButton.
     * It sets the text on the button to "Exit" and adds an ActionListener to exit the application upon user's click.
     */
    public ExitButton(){
        setText("Exit");
        addActionListener(e -> System.exit(0));
    }
}
