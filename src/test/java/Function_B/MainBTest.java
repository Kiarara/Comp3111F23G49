package Function_B;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

class MainBTest {
    @Test
    public void testMainB(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main_B.main(null);
        String expectedOutput = "Shortest path found!";
        assertEquals(expectedOutput, outContent.toString());
    }
}
