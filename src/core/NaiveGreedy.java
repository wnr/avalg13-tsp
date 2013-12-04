package core;

/**
 * @author mathiaslindblom
 */
public class NaiveGreedy {
    Vertex[] pointsCoordinates;
    int[]    pathTaken;
    int      numberOfPoints;
    int[]    distanceMatrix;

    public NaiveGreedy(Vertex[] inputCoords, int[] distanceMatrix) {
        numberOfPoints = inputCoords.length;
        pointsCoordinates = inputCoords;
        pathTaken = new int[numberOfPoints];
    }

    public NaiveGreedy() {
        numberOfPoints = 0;
        pointsCoordinates = null;
        pathTaken = null;
        distanceMatrix = null;
    }

    public void setVertices(Vertex[] vertices) {
        numberOfPoints = vertices.length;
        pointsCoordinates = vertices;
        pathTaken = new int[numberOfPoints];
    }

    public int[] findPath() {
        pathTaken[0] = 0;
        boolean[] usedPoints = new boolean[numberOfPoints];
        usedPoints[0] = true;
        for (int i = 1; i < numberOfPoints; i++) {
            int best = -1;
            for (int j = 0; j < numberOfPoints; j++) {
                if (!usedPoints[j] && (best == -1 || Utils.distance(pointsCoordinates[pathTaken[i - 1]], pointsCoordinates[j], distanceMatrix) < Utils.distance(pointsCoordinates[pathTaken[i - 1]], pointsCoordinates[best], distanceMatrix))) {
                    best = j;
                }
            }
            pathTaken[i] = best;
            usedPoints[best] = true;
        }
        return pathTaken;
    }
}
