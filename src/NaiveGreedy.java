/**
 * @author mathiaslindblom
 */
public class NaiveGreedy {
    float[] pointsCoordinates;
    int[]   pathTaken;
    int     numberOfPoints;

    public NaiveGreedy(float[] inputCoords) {
        numberOfPoints = inputCoords.length / 2;
        pointsCoordinates = inputCoords;
        pathTaken = new int[numberOfPoints];
    }

    public NaiveGreedy() {
        numberOfPoints = 0;
        pointsCoordinates = null;
        pathTaken = null;
    }

    public void setVertices(Utils.Vertex[] vertices) {
        numberOfPoints = vertices.length;
        pointsCoordinates = new float[numberOfPoints*2];
        pathTaken = new int[numberOfPoints];

        for(int i = 0; i < vertices.length; i++) {
            pointsCoordinates[i*2] = vertices[i].x;
            pointsCoordinates[i*2+1] = vertices[i].y;
        }
    }

    public int[] findPath() {
        pathTaken[0] = 0;
        boolean[] usedPoints = new boolean[numberOfPoints];
        usedPoints[0] = true;
        for (int i = 1; i < numberOfPoints; i++) {
            int best = -1;
            for (int j = 0; j < numberOfPoints; j++) {
                if (!usedPoints[j] && (best == -1 || dist(pathTaken[i - 1], j) < dist(pathTaken[i - 1], best))) {
                    best = j;
                }
            }
            pathTaken[i] = best;
            usedPoints[best] = true;
        }
        return pathTaken;
    }

    private int dist(int p1, int p2) {
        float x1 = pointsCoordinates[p1 * 2];
        float y1 = pointsCoordinates[p1 * 2 + 1];
        float x2 = pointsCoordinates[p2 * 2];
        float y2 = pointsCoordinates[p2 * 2 + 1];

        return (int) (Math.sqrt((x1 - x2) * (x1 - x2) + (y1-y2) * (y1-y2)) + 0.5);
    }
}
