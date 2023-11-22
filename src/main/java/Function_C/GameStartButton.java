package Function_C;

import Shared.Window;

import javax.swing.*;

/**
 * The GameStartButton implements a button for users to start or restart the game
 */
public class GameStartButton extends JButton {
    public GameStartButton(Window gameWindow, JFrame parent_frame, boolean isInitialStart){
        if (isInitialStart)
            setText("Start");
        else
            setText("Restart");

        addActionListener(e -> {
            parent_frame.dispose(); // dispose the Jframe to which the start button is added
            gameWindow.setMode(); // jump to the Jframe which allows users to set game mode
            if (isInitialStart)
                gameWindow.setVisible(true);
        });
    }

}
