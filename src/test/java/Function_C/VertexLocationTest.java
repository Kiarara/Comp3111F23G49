package Function_C;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VertexLocationTest {
    @Test
    void testVertexLocation(){
        VertexLocation loc = new VertexLocation(2,2); // target function
        assertEquals(2, loc.x);
        assertEquals(2, loc.y);
    }
    @Test
    void copyConstructorTest() {
        VertexLocation loc1 = new VertexLocation(1,3);
        VertexLocation loc2 = new VertexLocation(loc1); // target function
        assert(loc1.isSame(loc2));
    }
    @Test
    void updateLocation() {
        VertexLocation loc1 = new VertexLocation(1,3);
        loc1.updateLocation(1,2);
        int updated_x = loc1.x;
        int updated_y = loc1.y;
        assertEquals(updated_x, 1);
        assertEquals(updated_y, 2);
    }
    @Test
    void isSame() {
        VertexLocation loc1 = new VertexLocation(1,1);
        VertexLocation loc2 = new VertexLocation(1,1);
        boolean result = loc1.isSame(loc2);
        assertTrue(result);
    }
}
