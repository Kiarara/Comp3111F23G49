package Function_C;

import javax.swing.*;

public class ExitButton extends JButton {
    public ExitButton(){
        setText("Exit");
        addActionListener(e -> System.exit(0));
    }
}
