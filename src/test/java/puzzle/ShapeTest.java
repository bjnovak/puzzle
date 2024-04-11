package puzzle;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {

    @Test
    public void testClear() {
        Shape shape = new Shape();
        shape.addPoint(new Point(1, 1));
        shape.addPoint(new Point(5, 1));
        shape.addPoint(new Point(5, 5));
        assertEquals(shape.clear(), 0);
    }

    @Test
    public void testGenerateRandom() throws Exception {
        Shape shape = new Shape();
        shape.generateRandom();
        assertTrue(shape.isValidConvex());
    }

    @Test
    public void testAddPoint() {
        Shape shape = new Shape();
        assertTrue(shape.addPoint(new Point(1, 1)));
        assertTrue(shape.addPoint(new Point(5, 1)));
        assertTrue(shape.addPoint(new Point(5, 5)));
        assertFalse(shape.addPoint(new Point(4, 0)));
        assertFalse(shape.addPoint(new Point(1, 1)));
    }

    @Test
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Shape shape = new Shape();

        shape.print();

        assertEquals("", outContent.toString());

        assertTrue(shape.addPoint(new Point(1, 1)));
        assertTrue(shape.addPoint(new Point(5, 1)));
        assertTrue(shape.addPoint(new Point(5, 5)));

        shape.print();

        assertEquals("1:(1,1)\n2:(5,1)\n3:(5,5)\n", outContent.toString());

        System.setOut(System.out);
    }

    @Test
    public void testIsValidConvex() {
        Shape shape = new Shape();
        shape.addPoint(new Point(1, 1));
        shape.addPoint(new Point(5, 1));

        assertFalse(shape.isValidConvex());

        shape.addPoint(new Point(5, 5));

        assertTrue(shape.isValidConvex());
    }

    @Test
    public void testIsWithinShape() {
        Shape shape = new Shape();
        shape.addPoint(new Point(0, 0));
        shape.addPoint(new Point(10, 0));
        shape.addPoint(new Point(5, 10));
        assertTrue(shape.isValidConvex());
        assertTrue(shape.isWithinShape(new Point(0, 0)));
        assertTrue(shape.isWithinShape(new Point(5, 5)));
        assertTrue(shape.isWithinShape(new Point(3, 8)));
        assertFalse(shape.isWithinShape(new Point(11, 0)));
        assertFalse(shape.isWithinShape(new Point(5, 11)));
    }

}
