package Shared;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import javax.swing.ImageIcon;

public class SquarePanelTest {

    private SquarePanel squarePanel;

    @Before
    public void setUp() {
        squarePanel = new SquarePanel(Color.RED); // Assuming RED represents a specific color
    }

    @Test
    public void testChangeColor() {
        squarePanel.ChangeColor(Color.BLUE); // Assuming BLUE represents a different color
        assertEquals(Color.BLUE, squarePanel.getBackground());
    }

    @Test
    public void testSetImage() {
        ImageIcon testIcon = new ImageIcon("test_image.png"); // Assuming "test_image.png" is a valid image path
        squarePanel.setImage(testIcon);
        assertNotNull(squarePanel.getComponent(0)); // Assuming the image is added as the first component
    }

    @Test
    public void testClearObject() {
        squarePanel.clearObject();
        assertEquals(0, squarePanel.getComponentCount());
    }
}