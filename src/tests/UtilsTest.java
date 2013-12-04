package tests;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import core.Vertex;
import core.Utils;

public class UtilsTest {
    @Test
    public void testDistance() throws Exception {
        Vertex p1 = new Vertex();
        Vertex p2 = new Vertex();
        Assert.assertEquals(0f, Utils.distance(p1, p2), Utils.EPSILON);
        Assert.assertEquals(0f, Utils.distance(p2, p1), Utils.EPSILON);

        p1 = new Vertex(1412f, 0.1241f);
        p2 = p1;
        Assert.assertEquals(0f, Utils.distance(p1, p2), Utils.EPSILON);
        Assert.assertEquals(0f, Utils.distance(p2, p1), Utils.EPSILON);

        p1 = new Vertex();
        p2 = new Vertex(1f, 1f);
        Assert.assertEquals((int) Math.sqrt(2), Utils.distance(p1, p2), Utils.EPSILON);
        Assert.assertEquals((int) Math.sqrt(2), Utils.distance(p2, p1), Utils.EPSILON);

        p1 = new Vertex(4f, 0.12f);
        p2 = new Vertex(-1.2f, 1241.144f);
        Assert.assertEquals(1241.0, Utils.distance(p1, p2), Utils.EPSILON);
        Assert.assertEquals(1241.0, Utils.distance(p2, p1), Utils.EPSILON);
    }

    @Test
    public void testPathDistance() {
        Vertex[] vertices = { new Vertex(1f, 12f), new Vertex(19f, 2f), new Vertex(-1.2f, 12f), new Vertex(-9f, -9f) };
        {
            int[] indices = { 0, 3, 1 };
            float expected = Utils.distance(vertices[0], vertices[3]) + Utils.distance(vertices[3], vertices[1]) + Utils.distance(vertices[1], vertices[0]);
            float actual = Utils.pathDistance(indices, vertices);
            Assert.assertEquals(expected, actual, Utils.EPSILON);
        }
    }

    @Test
    public void testParseInput() {
        String input = "3\n0.12 -01.34\n141 155\n11 0";
        Vertex[] expected = { new Vertex(0.12f, -1.34f), new Vertex(141f, 155f), new Vertex(11f, 0f) };
        Vertex[] actual = Utils.parseInput(input);

        assertEquals(expected.length, actual.length);
        Assert.assertEquals(expected[0], actual[0]);
        Assert.assertEquals(expected[1], actual[1]);
        Assert.assertEquals(expected[2], actual[2]);
    }

    @Test
    public void testParseOutput() {
        {
            String output = "0\n1\n3\n5";
            int[] expected = { 0, 1, 3, 5 };
            int[] actual = Utils.parseOutput(output);
            assertEquals(expected.length, actual.length);
            Assert.assertEquals(expected[0], actual[0], Utils.EPSILON);
            Assert.assertEquals(expected[1], actual[1], Utils.EPSILON);
            Assert.assertEquals(expected[2], actual[2], Utils.EPSILON);
            Assert.assertEquals(expected[3], actual[3], Utils.EPSILON);
        }
        {
            String output = "1\n2\n3\n4";
            int[] expected = { 0, 1, 2, 3 };
            int[] actual = Utils.parseOutput(output);
            assertEquals(expected.length, actual.length);
            Assert.assertEquals(expected[0], actual[0], Utils.EPSILON);
            Assert.assertEquals(expected[1], actual[1], Utils.EPSILON);
            Assert.assertEquals(expected[2], actual[2], Utils.EPSILON);
            Assert.assertEquals(expected[3], actual[3], Utils.EPSILON);
        }
        {
            String output = "1\n2\n30\n4";
            int[] expected = { 0, 1, 29, 3 };
            int[] actual = Utils.parseOutput(output);
            assertEquals(expected.length, actual.length);
            Assert.assertEquals(expected[0], actual[0], Utils.EPSILON);
            Assert.assertEquals(expected[1], actual[1], Utils.EPSILON);
            Assert.assertEquals(expected[2], actual[2], Utils.EPSILON);
            Assert.assertEquals(expected[3], actual[3], Utils.EPSILON);
        }
    }

    @Test
    public void testPathToOutput() {
        int[] indices = { 4, 12, 41, 2, 5, 12 };
        String expected = "4\n12\n41\n2\n5\n12";
        String actual = Utils.pathToOutput(indices);
        assert (expected.equals(actual));
    }
}
