package Function_C;

import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.mockito.Mockito.verify;

public class ExitButtonTest {

    @Test
    public void testExitButton(){
        JFrame mock_parent_frame = Mockito.mock(JFrame.class);

        ExitButton button = new ExitButton();
        button.doClick();
        verify(mock_parent_frame).dispose();
    }
}
