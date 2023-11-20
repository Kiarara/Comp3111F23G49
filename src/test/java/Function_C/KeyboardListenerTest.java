package Function_C;

import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import static org.mockito.Mockito.*;


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