package Shared;

import javax.swing.*;
import java.awt.*;

/**
 * The GameControlFrame class represents a frame that controls the game functions.
 * It extends the JFrame class from the javax.swing package.
 *
 * @author LIU Muyuan(Oakley)
 */
public class GameControlFrame extends JFrame {

    /**
     * Constructs a GameControlFrame.
     * It sets the title of the frame, creates function buttons, and arranges them in a panel.
     * The panel is then added to the center of the frame using BorderLayout.
     */
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


    /**
     * The main method to start the application (Function A/B/C).
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {

        // Create a new control frame
        GameControlFrame controlFrame = new GameControlFrame();

        // Make the frame visible
        controlFrame.setVisible(true);
        controlFrame.setLocationRelativeTo(null);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}