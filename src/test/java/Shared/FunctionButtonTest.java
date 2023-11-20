package Shared;

import org.junit.Test;

public class FunctionButtonTest {
    @Test
    public void testFunctionButton(){

        FunctionButton button_1 = new FunctionButton(0); // target function
        FunctionButton button_2 = new FunctionButton(1); // target function
        FunctionButton button_3 = new FunctionButton(2); // target function

        button_1.doClick();
        button_2.doClick();
        button_3.doClick();
    }
}
