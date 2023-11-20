package Function_C;

import Shared.Window;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.awt.event.KeyEvent;


public class KeyboardListenerTest {
    @Test
    public void testKeyPressed() {
        KeyboardListener keyboardListener = new KeyboardListener();
        KeyEvent mockKeyEvent = mock(KeyEvent.class);

        // Simulate a key press event, for example, the right arrow key
        when(mockKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
        keyboardListener.keyPressed(mockKeyEvent);

        when(mockKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
        keyboardListener.keyPressed(mockKeyEvent);

        when(mockKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_UP);
        keyboardListener.keyPressed(mockKeyEvent);

        when(mockKeyEvent.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
        keyboardListener.keyPressed(mockKeyEvent);
        }
}