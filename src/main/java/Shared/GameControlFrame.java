package Shared;

import Function_A.Main_A;
import Function_B.Main_B;
import Function_C.Main_C;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameControlFrame extends JFrame {
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public GameControlFrame() {
        // Set the title of the frame
        super("Game Control");

        // Create the buttons
        button1 = new JButton("Function A");
        button2 = new JButton("Function B");
        button3 = new JButton("Function C");

        // Set custom font for buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        button1.setFont(buttonFont);
        button2.setFont(buttonFont);
        button3.setFont(buttonFont);

        // Set custom colors for buttons
        button1.setBackground(Color.WHITE);
        button1.setForeground(Color.BLACK);
        button2.setBackground(Color.WHITE);
        button2.setForeground(Color.BLACK);
        button3.setBackground(Color.WHITE);
        button3.setForeground(Color.BLACK);

        // Add action listeners to the buttons
        button1.addActionListener(new Button1Listener());
        button2.addActionListener(new Button2Listener());
        button3.addActionListener(new Button3Listener());

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(button1);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(button2);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(button3);

        // Create an empty panel for center alignment
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.add(buttonPanel);

        // Set the layout manager of the main frame
        setLayout(new BorderLayout());

        // Add the center panel to the center of the main frame
        add(centerPanel, BorderLayout.CENTER);


        // Set the size of the frame
        setSize(200, 200);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        setVisible(true);
        setLocationRelativeTo(null);
    }

    // Define the action listeners for each button
    private class Button1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Main_A.main(null);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private class Button2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Main_B.main(null);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private class Button3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Main_C.main(null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Set the look and feel of the application to the system's default
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Create the game control frame
                GameControlFrame controlFrame = new GameControlFrame();
            }
        });
    }
}