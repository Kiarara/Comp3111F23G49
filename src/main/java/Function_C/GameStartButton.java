package Function_C;

import Shared.Window;

import javax.swing.*;

public class GameStartButton extends JButton {
    public GameStartButton(Window gameWindow, JFrame parent_frame, boolean isInitialStart){
        if (isInitialStart)
            setText("Start");
        else
            setText("Restart");

        addActionListener(e -> {
            parent_frame.dispose();
            gameWindow.setMode();
            if (isInitialStart)
                gameWindow.setVisible(true);
        });
    }

}
