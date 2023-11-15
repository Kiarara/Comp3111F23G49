package Function_C;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class VertexLocationTest {
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
