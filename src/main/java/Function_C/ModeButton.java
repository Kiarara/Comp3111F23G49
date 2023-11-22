package Function_C;

import Shared.Window;

import javax.swing.*;

/**
 * The ModeButton implements a button for users to select a difficulty level for the game
 * 3 different difficulty levels are available: easy, medium, hard
 */
public class ModeButton extends JButton {
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

        // upon users' clicks, close the option window and initialize the game window
        addActionListener(f-> {
            game_window.start_game(mode);
            parent_window.dispose();
        });
    }
}
