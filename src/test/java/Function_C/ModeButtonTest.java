package Function_C;

import static org.mockito.Mockito.*;

import javax.swing.JFrame;

import org.junit.Test;
import org.mockito.Mockito;

import Shared.Window;

public class ModeButtonTest {

    @Test
    public void testModeButton() {

        Window mock_gameWindow = Mockito.mock(Window.class);
        for (int i: new int[] {0,1,2}){
            JFrame mock_parent_frame = Mockito.mock(JFrame.class);
            ModeButton mock_button = new ModeButton(i, mock_parent_frame, mock_gameWindow);
            mock_button.doClick();
            verify(mock_parent_frame).dispose();
        }
    }
}