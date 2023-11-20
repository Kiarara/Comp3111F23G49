package Shared;

import javax.swing.*;
import java.awt.*;
public class GameControlFrame extends JFrame {

    public GameControlFrame() {
        // Set the title of the frame
        super("Game Control");

        // Create the buttons
        FunctionButton function_a = new FunctionButton(0);
        FunctionButton function_b = new FunctionButton(1);
        FunctionButton function_c = new FunctionButton(2);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(function_a);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(function_b);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(function_c);

        // Create an empty panel for center alignment
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(buttonPanel);

        // Set the layout of the game control panel
        setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        setSize(200, 200);


    }

    public static void main(String[] args) {

        GameControlFrame controlFrame = new GameControlFrame();

        // Make the frame visible
        controlFrame.setVisible(true);
        controlFrame.setLocationRelativeTo(null);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}