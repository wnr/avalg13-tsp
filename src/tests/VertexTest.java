package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Vertex;
import core.Utils;

public class VertexTest {
    @Test
    public void testVertex() throws Exception {
        Vertex p = new Vertex();
        assertEquals(0f, p.x, Utils.EPSILON);
        assertEquals(0f, p.y, Utils.EPSILON);
        assertEquals(p, new Vertex());
        assertEquals(p.toString(), "(0, 0)");


        p = new Vertex(-1.13f, 1124f);
        assertEquals(-1.13f, p.x, Utils.EPSILON);
        assertEquals(1124f, p.y, Utils.EPSILON);
        assertEquals(p, new Vertex(-1.13f, 1124f));
        assertEquals(p.toString(), "(-1,13, 1124)");

        p = new Vertex(0f, 1.14122f);
        assertEquals(p.toString(), "(0, 1,14)");
    }
}
