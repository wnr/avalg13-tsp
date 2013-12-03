import org.junit.Before;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Tester {
    ArrayList<Utils.Vertex[]> inputs;
    ArrayList<Integer[]> optPaths;

    @Before
    public void setUp() throws Exception {
        for(int i = 1; ; i++) {
            try {
                inputs.add(Utils.parseInput(Utils.readFile("test/graph" + i + ".in", Charset.defaultCharset())));
                optPaths.add(Utils.parseOutput(Utils.readFile("test/graph" + i + ".out", Charset.defaultCharset())));
            } catch (IOException e) {
                break;
            }
        }
    }


}
