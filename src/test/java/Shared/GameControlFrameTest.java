package Shared;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class GameControlFrameTest {

    @Test
    public void setUp() {
        GameControlFrame control_frame = new GameControlFrame(); // target function
        assert(control_frame.isActive());
    }

    @Test
    public void testMain(){
        GameControlFrame.main(null); // target function

        // Create a test instance of the GameControlFrame class
        GameControlFrame controlFrame = new GameControlFrame();

        // Check if the frame is visible, location is relative to null, and exit behavior is set to EXIT_ON_CLOSE
        assertEquals(true, controlFrame.isVisible());
        assertEquals(0, controlFrame.getX());
        assertEquals(0, controlFrame.getY());
        assertEquals(JFrame.EXIT_ON_CLOSE, controlFrame.getDefaultCloseOperation());

    }
}