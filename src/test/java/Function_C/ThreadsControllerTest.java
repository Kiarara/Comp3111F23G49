package Function_C;

import Shared.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

/*
public class ThreadsControllerTest {
    private Window window;
    private ThreadsController c;

    @BeforeEach
    void setUp() {
        window = new Window(true);
        // Create a flag to track if the button action is performed
        boolean buttonClicked = false;

        // Create a JFrame and JButton similar to the confirmGameStart() method
        JFrame frame = new JFrame("Welcome to the game!");
        JButton button = new JButton("Click to Start");

        // Add an ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the flag to true when the button is clicked
                buttonClicked = true;
            }
        });

        // Add the button to the frame and set other properties
        frame.getContentPane().add(button, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Simulate button click
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                button.doClick();
            }
        });

        // Wait for a short time to allow the action to be performed
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert that the button action was performed
        assertTrue(buttonClicked);

        // Assert any other expectations or assertions related to the confirmGameStart() method
        // ...

        // Simulate key press event
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                KeyListener[] keyListeners = yourClass.getKeyListeners();
                for (KeyListener listener : keyListeners) {
                    listener.keyPressed(new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED));
                }
            }
        });

        // Wait for a short time to allow the action to be performed
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert any expectations or assertions related to the key press event
        // ...
    }
        }


    @Test
    void gameInitialize() {
        controller.game_initialize();

        assertNotNull(controller.m);
        assertNotNull(controller.finder);
        assertNotNull(controller.tomPos);
        assertNotNull(controller.jerryPos);
        assertNotNull(controller.freezerLocs);
        assertNotNull(controller.tuffyPos);
        assertNotNull(controller.Squares);
        assertNotNull(controller.shortest_path_for_jerry);
    }

    @Test
    void run() {
        // Test the run method
        // Modify or add assertions as needed
        assertDoesNotThrow(() -> {
            controller.start();
            Thread.sleep(1000); // Wait for some time
            controller.running = false;
            controller.join();
        });
    }
}

 */