package core;

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

    public void setVertices(Vertex[] vertices) {
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
                if (!usedPoints[j] && (best == -1 || Commons.dist(pathTaken[i - 1], j, pointsCoordinates) < Commons.dist(pathTaken[i - 1], best, pointsCoordinates))) {
                    best = j;
                }
            }
            pathTaken[i] = best;
            usedPoints[best] = true;
        }
        return pathTaken;
    }


}
