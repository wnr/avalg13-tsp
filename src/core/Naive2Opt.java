package core;

public class Naive2Opt {
    public static void run(Vertex[] path, int[] distances, long endTime) {
        int gain = 0;
        int[] best = new int[2];

        //Loop until a gain is found.
        while (gain == 0 && System.currentTimeMillis() < endTime) {
            int pathLength = path.length;

            //Loop through all vertices in path.
            for (int i = 0; i < path.length; i++) {
                if(System.currentTimeMillis() > endTime){
                    break;
                }

                //Pick one edge.
                Edge edge1 = new Edge(path[i], path[(i + 1) % pathLength]);

                //Loop from next free vertex in path to the last free vertex.
                //Free vertex = vertex that is not in edge1.
                for (int j = i + 2; j < pathLength; j++) {
                    if (i == 0 && (j + 1) == pathLength) {
                        //edge1 contains the first vertex in path, which is connected to
                        //the last vertex in path, so we cannot use it.
                        continue;
                    }
                    if(System.currentTimeMillis() > endTime){
                        break;
                    }

                    //Pick the second edge.
                    //TODO remove creating new edges (Objects) all the time.
                    Edge edge2 = new Edge(path[j], path[(j + 1) % pathLength]);

                    //Compute the sum of the old edge distances.
                    int oldDistance = Utils.distance(edge1.u, edge1.v, distances, pathLength) + Utils.distance(edge2.u, edge2.v, distances, pathLength);

                    //Comptue the sum of the edge distances if the other combination of the vertices is used.
                    int newDistance = Utils.distance(edge1.u, edge2.u, distances, pathLength) + Utils.distance(edge1.v, edge2.v, distances, pathLength);

                    //Compute the improvement.
                    int newGain = newDistance - oldDistance;

                    if (newGain < gain) {
                        //We have improved the path.
                        //Save the edges and the gain, because there could be a better gain.
                        gain = newGain;
                        best[0] = i;
                        best[1] = j;
                    }
                }
            }

            if (gain < 0) {
                //Swap.
                int swapSize = best[1] - best[0];
                //                if (swapSize < path.length / 2) { TODO reverse the "other" part of path if that part is smaller
                Vertex[] swapArrayPart = new Vertex[swapSize];
                System.arraycopy(path, best[0] + 1, swapArrayPart, 0, swapArrayPart.length);
                for (int i = 0; i < swapSize; i++) {
                    path[best[0] + 1 + i] = swapArrayPart[swapArrayPart.length-1 - i];
                }
//                System.out.println(Utils.pathDistance(path));
            }
        }
    }
}
