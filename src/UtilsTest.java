import org.junit.Test;
import static org.junit.Assert.*;

public class UtilsTest {
    @Test
    public void testPoint() throws Exception {
        Utils.Point p = new Utils.Point();
        assertEquals(0f, p.x, Utils.EPSILON);
        assertEquals(0f, p.y, Utils.EPSILON);

        p = new Utils.Point(-1.13f, 1124f);
        assertEquals(-1.13f, p.x, Utils.EPSILON);
        assertEquals(1124f, p.y, Utils.EPSILON);
    }

    @Test
    public void testDistance() throws Exception {
        Utils.Point p1 = new Utils.Point();
        Utils.Point p2 = new Utils.Point();
        assertEquals(0f, Utils.distance(p1, p2), Utils.EPSILON);
        assertEquals(0f, Utils.distance(p2, p1), Utils.EPSILON);

        p1 = new Utils.Point(1412f, 0.1241f);
        p2 = p1;
        assertEquals(0f, Utils.distance(p1, p2), Utils.EPSILON);
        assertEquals(0f, Utils.distance(p2, p1), Utils.EPSILON);

        p1 = new Utils.Point();
        p2 = new Utils.Point(1f, 1f);
        assertEquals(Math.sqrt(2), Utils.distance(p1, p2), Utils.EPSILON);
        assertEquals(Math.sqrt(2), Utils.distance(p2, p1), Utils.EPSILON);

        p1 = new Utils.Point(4f, 0.12f);
        p2 = new Utils.Point(-1.2f, 1241.144f);
        assertEquals(1241.035, Utils.distance(p1, p2), Utils.EPSILON);
        assertEquals(1241.035, Utils.distance(p2, p1), Utils.EPSILON);
    }
}
