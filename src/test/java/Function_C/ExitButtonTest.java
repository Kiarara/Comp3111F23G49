package Function_C;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;


public class ExitButtonTest {

    @Test
    public void testExitButton() throws Exception {

        ExitButton exitButton = new ExitButton(); // target function

        // Catch exit with SystemLamda library
        // Code that calls System.exit()
        int exitStatus = catchSystemExit(exitButton::doClick);

        assertEquals(0, exitStatus);
    }
}
