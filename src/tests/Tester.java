package tests;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;

import core.*;

public class Tester {
    ArrayList<Vertex[]> inputs;
    ArrayList<Integer>  optDistances;

    @Before
    public void setUp() throws Exception {
        inputs = new ArrayList<Vertex[]>();
        optDistances = new ArrayList<Integer>();

        for (int i = 0; ; i++) {
            try {
                inputs.add(Utils.parseInput(Utils.readFile("test/graph" + i + ".in", Charset.defaultCharset())));
                optDistances.add(Integer.parseInt(Utils.readFile("test/graph" + i + ".out", Charset.defaultCharset())));
            }
            catch (IOException e) {
                break;
            }
        }
    }

    private void comparePaths(int[] path, int optDistance, Vertex[] vertices, String name, long time) {
        float dist = Utils.pathDistance(path, vertices);

        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println(name + " found path \t" + df.format((optDistance / dist) * 100f) + "%\t\tof optimal path in \t" + time + "ms.");
    }

    @Test
    public void testNaiveGreedy() {
        NaiveGreedy ng = new NaiveGreedy();

        {
            String input = "10\n" + "95.0129 61.5432\n" + "23.1139 79.1937\n" + "60.6843 92.1813\n" + "48.5982 73.8207\n" + "89.1299 17.6266\n" + "76.2097 40.5706\n" + "45.6468 93.5470\n" + "1.8504 91.6904\n" + "82.1407 41.0270\n" + "44.4703 89.3650";
            String output = "0\n" + "8\n" + "5\n" + "4\n" + "3\n" + "9\n" + "6\n" + "2\n" + "1\n" + "7";
            ng.setVertices(Utils.parseInput(input));
            String actual = Utils.pathToOutput(ng.findPath());
            assert (output.equals(actual));
        }

        for (int i = 0; i < inputs.size(); i++) {
            Vertex[] vertices = inputs.get(i);
            long start = System.currentTimeMillis();
            ng.setVertices(vertices);
            int[] path = ng.findPath();
            long elapsed = System.currentTimeMillis() - start;
            comparePaths(path, optDistances.get(i), vertices, "Naive Greedy", elapsed);
        }
    }
}
