package Function_C;

import Shared.Window;

import javax.swing.*;

public class GameStartButton extends JButton {
    public GameStartButton(Window gameWindow, JFrame parent_frame){
        setText("Click to start");
        addActionListener(e -> {
            parent_frame.dispose();
            gameWindow.setMode();
            gameWindow.setVisible(true);
        });
    }

}
