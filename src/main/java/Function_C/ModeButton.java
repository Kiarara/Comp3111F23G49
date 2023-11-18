package Function_C;

import Shared.Window;

import javax.swing.*;

public class ModeButton extends JButton {
    public ModeButton(int mode, JFrame parent_window, Window game_window){
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
        addActionListener(f-> {
            game_window.start_game(mode);
            parent_window.dispose();
        });
    }


}
