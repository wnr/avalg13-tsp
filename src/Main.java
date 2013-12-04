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
        float[] inputCoords = new float[points*2];
        String[] pointCoord;
        for (int i = 0; i < points; i++) {
            pointCoord = in.readLine().split(" ");
            inputCoords[i * 2] = Float.parseFloat(pointCoord[0]);
            inputCoords[i * 2 + 1] = Float.parseFloat(pointCoord[1]);
        }

        NaiveGreedy naiveGreedy = new NaiveGreedy(inputCoords);
        int[] bestPath = naiveGreedy.findPath();

        Commons.ThreeOptimize(bestPath, inputCoords);
        for(int point: bestPath){
            System.out.println(point);
        }
    }
}
