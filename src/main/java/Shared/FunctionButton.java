package Shared;

import Function_A.Main_A;
import Function_B.Main_B;
import Function_C.Main_C;

import javax.swing.*;
import java.awt.*;

/**
 * Constructs a FunctionButton object with the specified function code.
 * function code: 0 for Function A, 1 for Function B, 2 for Function C
 *
 * @author LIU Muyuan(Oakley)
 */
public class FunctionButton extends JButton {

    /**
     * Represents a button for invoking different functions.
     *
     * @param function The code representing the specific function associated with the button.
     */
    public FunctionButton(int function){
        // set button texts
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

        /**
         * ActionListener for the function button.
         * Upon user's click, it invokes the specific function based on the function code.
         *
         * @param e The ActionEvent object representing the user's click.
         */
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
