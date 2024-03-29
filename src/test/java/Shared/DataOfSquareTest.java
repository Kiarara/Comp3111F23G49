package Shared;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataOfSquareTest {
    private DataOfSquare dataOfSquare;

    @Before
    public void setUp() {
        dataOfSquare = new DataOfSquare(0);
    }

    @Test
    public void testDataOfSqaures() {
        DataOfSquare data = new DataOfSquare(0); // target function
        assertEquals(0, data.getColor());
        assertEquals(3, data.C.size());
        assertEquals(4, data.I.size());
        assertEquals(-1, data.getObject());
    }
    @Test
    public void testLightMeUp() {
        dataOfSquare.lightMeUp(1);
        assertEquals(1, dataOfSquare.color);
    }

    @Test
    public void testChangeObject() {
        dataOfSquare.changeObject(2);
        assertEquals(2, dataOfSquare.obj);
    }

    @Test
    public void testClearObject() {
        dataOfSquare.clearObject();
        assertEquals(-1, dataOfSquare.obj);
    }

    @Test
    public void testGetObject() {
        assertEquals(-1, dataOfSquare.getObject());
    }

    @Test
    public void testGetColor(){
        assertEquals(0, dataOfSquare.getColor());
    }
}
