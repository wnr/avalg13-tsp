import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Tester {
    ArrayList<Utils.Vertex[]> inputs;
    ArrayList<int[]> optPaths;

    @Before
    public void setUp() throws Exception {
        inputs = new ArrayList<Utils.Vertex[]>();
        optPaths = new ArrayList<int[]>();

        for(int i = 1; ; i++) {
            try {
                inputs.add(Utils.parseInput(Utils.readFile("test/graph" + i + ".in", Charset.defaultCharset())));
                optPaths.add(Utils.parseOutput(Utils.readFile("test/graph" + i + ".out", Charset.defaultCharset())));
            } catch (IOException e) {
                break;
            }
        }
    }

    private void comparePaths(int[] path, int[] optPath, Utils.Vertex[] vertices, String name) {
        float dist = Utils.pathDistance(path, vertices);
        float optDist = Utils.pathDistance(optPath, vertices);

        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println(name + " found path " + df.format((optDist / dist)*100f) + " of optimal path.");
    }

    @Test
    public void testNaiveGreedy() {
        NaiveGreedy ng = new NaiveGreedy();

        {
            String input = "10\n" + "95.0129 61.5432\n" + "23.1139 79.1937\n" + "60.6843 92.1813\n" + "48.5982 73.8207\n" + "89.1299 17.6266\n" + "76.2097 40.5706\n" + "45.6468 93.5470\n" + "1.8504 91.6904\n" + "82.1407 41.0270\n" + "44.4703 89.3650";
            String output = "0\n" + "8\n" + "5\n" + "4\n" + "3\n" + "9\n" + "6\n" + "2\n" + "1\n" + "7";
            ng.setVertices(Utils.parseInput(input));
            String actual = Utils.pathToOutput(ng.findPath());
            assert(output.equals(actual));
        }

        for(int i = 0; i < inputs.size(); i++) {
            Utils.Vertex[] vertices = inputs.get(i);
            ng.setVertices(vertices);
            int[] path = ng.findPath();

            comparePaths(path, optPaths.get(i), vertices, "Naive Greedy");
        }
    }
}
