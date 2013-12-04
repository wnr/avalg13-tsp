package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author mathiaslindblom
 */
public class Main {

    public static void main(String[] args) throws Exception {
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
        int[] bestPath = naiveGreedy.findPath();

        for (int point : bestPath) {
            System.out.println(point);
        }
    }
}
