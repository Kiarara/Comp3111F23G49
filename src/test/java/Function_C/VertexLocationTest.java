package Function_C;

import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
public class VertexLocationTest {
    @Test
    void mySort() {
        int[] input = new int[] {2, 3, 1, 4, 5};
        int[] expected = new int[] {1, 2, 3, 4, 5};
        //int[] actual = Library.mySort(input);
        //assertArrayEquals(expected, actual);
    }

    @Test
    void detectEvent1() {
        VertexLocation loc1 = VertexLocation(1,1);

        boolean expect1 = true;
        boolean actual1 = L;

        assertEquals(expect1, actual1);
    }
}