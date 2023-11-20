package Shared;

import org.junit.Test;

public class GameControlFrameTest {

    @Test
    public void setUp() {
        GameControlFrame control_frame = new GameControlFrame(); // target function
        assert(control_frame.isActive());
    }

    @Test
    public void testMain(){
        GameControlFrame.main(null);
    }
}