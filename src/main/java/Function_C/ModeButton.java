package Function_C;

import Shared.Window;

import javax.swing.*;

/**
 * The ModeButton implements a button for users to select a difficulty level for the game
 * 3 different difficulty levels are available: easy, medium, hard
 *
 * @author PENG Xinyin(Kiara)
 */
public class ModeButton extends JButton {

    /**
     * Represents a mode button that allows the user to select a difficulty mode for the game.
     *
     * @param mode          The mode value representing the difficulty level (0 for Easy, 1 for Medium, 2 for Hard).
     * @param parent_window The JFrame object representing the parent window that contains the mode button.
     * @param game_window   The Window object representing the game window.
     */
    public ModeButton(int mode, JFrame parent_window, Window game_window){

        // set the texts on the button
        switch (mode){
            case 0:
                setText("Easy");
                break;
            case 1:
                setText("Medium");
                break;
            case 2:
                setText("Hard");
        }

        /**
         * ActionListener for the mode button.
         * Upon user's click, it starts the game with the selected mode and closes the option window.
         *
         * @param f The ActionEvent object representing the user's click.
         */
        addActionListener(f-> {
            game_window.start_game(mode);
            parent_window.dispose();
        });
    }
}
