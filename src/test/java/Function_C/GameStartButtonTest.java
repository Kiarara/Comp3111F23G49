package Function_C;

import static org.mockito.Mockito.*;

import javax.swing.JFrame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import Shared.Window;

@RunWith(MockitoJUnitRunner.class)
public class GameStartButtonTest {
    @Mock
    Window mock_gameWindow = Mockito.mock(Window.class);
    JFrame mock_parent_frame = Mockito.mock(JFrame.class);

    @Test
    public void testGameStartButton() {

        GameStartButton button = new GameStartButton(mock_gameWindow, mock_parent_frame);

        // Simulate button click
        button.doClick();

        // Verify that the parent_frame is disposed
        verify(mock_parent_frame).dispose();

        // Verify that the gameWindow is set to visible
        verify(mock_gameWindow).setVisible(true);
    }
}
