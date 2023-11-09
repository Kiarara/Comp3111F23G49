package Function_C;

import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
public class VertexLocationTest {
    @Test
    void updateLocation() {
        VertexLocation loc1 = new VertexLocation(1,3);
        loc1.updateLocation(1,2);
        int updated_x = loc1.getX();
        int updated_y = loc1.getY();
        assertEquals(updated_x, 1);
        assertEquals(updated_y, 2);
    }
    @Test
    void isSame() {
        VertexLocation loc1 = new VertexLocation(1,1);
        VertexLocation loc2 = new VertexLocation(1,1);
        boolean result = loc1.isSame(loc2);
        assertEquals(result, true);
    }
}
