package Shared;

import Function_A.Main_A;
import Function_B.Main_B;
import Function_C.Main_C;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FunctionButton extends JButton {
    public FunctionButton(int function){
        // set text

        // set button font
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        setFont(buttonFont);
        switch (function){
            case 0:
                setText("Function A");
                break;
            case 1:
                setText("Function B");
                break;
            case 2:
                setText("Function C");
                break;
        }

        // set button color
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        addActionListener(e -> {
            switch (function){
                case 0:
                    Main_A.main(null);
                    break;
                case 1:
                    Main_B.main(null);
                    break;
                case 2:
                    Main_C.main(null);
                    break;
            }
        });
    }
}
