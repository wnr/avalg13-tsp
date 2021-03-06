package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author mathiaslindblom
 */
public class Main {
    private static final boolean DEBUG = true;
    private static final int END_TIME = 1700;

    public static void main(String[] args) throws Exception {
        long endTime = System.currentTimeMillis() + END_TIME;
        BufferedReader in;

        //        in = new BufferedReader(new FileReader("some_file.txt"));
        in = new BufferedReader(new InputStreamReader(System.in));

        int points = Integer.parseInt(in.readLine());
        Vertex[] inputCoords = new Vertex[points];
        String[] pointCoord;
        for (int i = 0; i < points; i++) {
            pointCoord = in.readLine().split(" ");
            Vertex v = new Vertex(Float.parseFloat(pointCoord[0]), Float.parseFloat(pointCoord[1]), i);
            inputCoords[i] = v;
        }

        int[] distanceMatrix = Utils.computeDistanceMatrix(inputCoords);

        NaiveGreedy naiveGreedy = new NaiveGreedy(inputCoords, distanceMatrix);
        Vertex[] bestPath = naiveGreedy.findPath();

//        System.out.println(Utils.pathToOutput(bestPath));

//        System.out.println(Utils.pathDistance(bestPath));
        Naive2Opt.run(bestPath, distanceMatrix, endTime);
        System.out.println(Utils.pathToOutput(bestPath));
//        System.out.println(Utils.pathDistance(bestPath));
    }
}
