package Function_C;

import Shared.Window;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class MainCTest {

    @Test
    void testMainC() {

        Main_C.main(null); // target function

        Window f1 = new Window();

        // Check if the window title, size, and exit behavior are as expected
        assertEquals("Escape from Tom", f1.getTitle());
        assertEquals(900, f1.getWidth());
        assertEquals(900, f1.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, f1.getDefaultCloseOperation());
    }
}
