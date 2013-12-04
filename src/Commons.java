/**
 * @author mathiaslindblom
 */
public class Commons {

    public static void ThreeOptimize(int[] path, float[] coordinates) {
        for (int e1 = 0; e1 < path.length; e1++) {
            for (int e2 = e1 + 1; e2 < path.length; e2++) {
                for (int e3 = e2 + 1; e3 < path.length; e3++) {

                }
            }
        }
    }

    public static int dist(int p1, int p2, float[] pointsCoordinates) {
        float x1 = pointsCoordinates[p1 * 2];
        float y1 = pointsCoordinates[p1 * 2 + 1];
        float x2 = pointsCoordinates[p2 * 2];
        float y2 = pointsCoordinates[p2 * 2 + 1];

        return (int) (Math.sqrt((x1 - x2) * (x1 - x2) + (y1-y2) * (y1-y2)) + 0.5);
    }
}
