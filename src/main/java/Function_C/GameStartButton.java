package Function_C;

import Shared.Window;

import javax.swing.*;

/**
 * The GameStartButton implements a button for users to start or restart the game
 *
 * @author PENG Xinyin(Kiara)
 */
public class GameStartButton extends JButton {

    /**
     * Represents a button for starting or restarting the game.
     *
     * @param gameWindow       The Window object representing the game window.
     * @param parent_frame     The JFrame object representing the parent frame that contains the start button.
     * @param isInitialStart   A boolean value indicating whether it is the initial start of the game.
     */
    public GameStartButton(Window gameWindow, JFrame parent_frame, boolean isInitialStart){
        if (isInitialStart)
            setText("Start");
        else
            setText("Restart");

        /**
         * ActionListener for the start button.
         * Upon user's click, it disposes the parent frame, sets the game mode in the game window,
         * and makes the game window visible if it is the initial start.
         *
         * @param e The ActionEvent object representing the user's click.
         */
        addActionListener(e -> {
            parent_frame.dispose(); // dispose the Jframe to which the start button is added
            gameWindow.setMode();   // jump to the Jframe which allows users to set game mode
            if (isInitialStart)
                gameWindow.setVisible(true);
        });
    }
}
