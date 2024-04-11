package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

    @Test
    public void testPointValues() {
        Point p = new Point(1, 2);
        assertEquals(1, p.x());
        assertEquals(2, p.y());
    }

}

