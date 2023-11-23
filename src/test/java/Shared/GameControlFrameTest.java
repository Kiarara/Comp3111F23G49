package Shared;

import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameControlFrameTest {

    @Test
    public void setUp() {
        GameControlFrame control_frame = new GameControlFrame(); // target function
        // Verify the frame title
        assertEquals("Game Control", control_frame.getTitle());

        // Verify the layout of the game control panel
        assertTrue(control_frame.getLayout() instanceof BorderLayout);

        // Verify the size of the frame
        assertEquals(new Dimension(200, 200), control_frame.getSize());

        // Verify the existence and layout of the center panel
        Container contentPane = control_frame.getContentPane();
        assertTrue(contentPane instanceof JPanel);
        JPanel centerPanel = (JPanel) contentPane.getComponent(0);
        assertTrue(centerPanel.getLayout() instanceof GridBagLayout);

        // Verify the existence and layout of the button panel
        JPanel buttonPanel = (JPanel) centerPanel.getComponent(0);
        assertTrue(buttonPanel.getLayout() instanceof BoxLayout);

        // Verify the number of buttons and fillers in the button panel
        assertEquals(5, buttonPanel.getComponentCount());

        // Verify the type of the buttons
        assertTrue(buttonPanel.getComponent(0) instanceof FunctionButton);
        assertTrue(buttonPanel.getComponent(1) instanceof Box.Filler);
        assertTrue(buttonPanel.getComponent(2) instanceof FunctionButton);
        assertTrue(buttonPanel.getComponent(3) instanceof Box.Filler);
        assertTrue(buttonPanel.getComponent(4) instanceof FunctionButton);
    }

    @Test
    public void testMain(){
        GameControlFrame.main(null); // target function
    }
}