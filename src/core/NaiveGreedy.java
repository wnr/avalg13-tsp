package core;

/**
 * @author mathiaslindblom
 */
public class NaiveGreedy {
    Vertex[] pointsCoordinates;
    Vertex[]    pathTaken;
    int      numberOfPoints;
    int[]    distanceMatrix;

    public NaiveGreedy(Vertex[] inputCoords, int[] distanceMatrix) {
        numberOfPoints = inputCoords.length;
        pointsCoordinates = inputCoords;
        pathTaken = new Vertex[numberOfPoints];
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
        pathTaken = new Vertex[numberOfPoints];
    }

    public Vertex[] findPath() {
        pathTaken[0] = pointsCoordinates[0];
        boolean[] usedPoints = new boolean[numberOfPoints];
        usedPoints[0] = true;
        for (int i = 1; i < numberOfPoints; i++) {
            Vertex best = null;
            for (int j = 0; j < numberOfPoints; j++) {
                if (!usedPoints[j] && (best == null || Utils.distance(pathTaken[i - 1], pointsCoordinates[j], distanceMatrix) < Utils.distance(pathTaken[i - 1], best, distanceMatrix))) {
                    best = pointsCoordinates[j];
                }
            }
            pathTaken[i] = best;
            usedPoints[best.index] = true;
        }
        return pathTaken;
    }
}
