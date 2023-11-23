package Function_C;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import javax.swing.JFrame;

import org.junit.Test;
import org.mockito.Mockito;

import Shared.Window;

public class GameStartButtonTest {
    @Test
    public void testGameStartButton() {

        Window mock_gameWindow = Mockito.mock(Window.class);
        JFrame mock_parent_frame = Mockito.mock(JFrame.class);

        GameStartButton button_1 = new GameStartButton(mock_gameWindow, mock_parent_frame, true);
        GameStartButton button_2 = new GameStartButton(mock_gameWindow,mock_parent_frame,false);

        // Simulate button click
        button_1.doClick();

        assertEquals("Restart", button_2.getText());

        // Verify that the parent_frame is disposed
        verify(mock_parent_frame).dispose();

        // Verify that the gameWindow is set to visible
        verify(mock_gameWindow).setVisible(true);

    }

}
