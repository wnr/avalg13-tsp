package core;

public class Naive3Opt {
    private static final boolean SWAP_ASAP = true;

    public static void run(Vertex[] path, int[] distances, long endTime) {
        boolean abortLoop = false;
        int gain = 0;
        int[] best = new int[2];
        Vertex e1u;
        Vertex e1v;
        Vertex e2u;
        Vertex e2v;
        Vertex e3u;
        Vertex e3v;

        //Loop until a gain is found.
        while (gain == 0 && !abortLoop) {
            int pathLength = path.length;

            //Loop through all vertices in path.
            for (int i = 0; i < path.length; i++) {
                if (System.currentTimeMillis() > endTime) {
                    abortLoop = true;
                    break;
                }

                //Pick one edge.
                //                Edge edge1 = new Edge(path[i], path[(i + 1) % pathLength]);
                e1u = path[i];
                e1v = path[(i + 1) % pathLength];

                //Loop from next free vertex in path to the last free vertex.
                //Free vertex = vertex that is not in edge1.
                for (int j = i + 2; j < pathLength; j++) {
                    if (i == 0 && (j + 1) == pathLength) {
                        //edge1 contains the first vertex in path, which is connected to
                        //the last vertex in path, so we cannot use it.
                        continue;
                    }
                    //                    if(System.currentTimeMillis() > endTime){
                    //                        break;
                    //                    }

                    //Pick the second edge.
                    //TODO remove creating new edges (Objects) all the time.
                    //                    Edge edge2 = new Edge(path[j], path[(j + 1) % pathLength]);
                    e2u = path[j];
                    e2v = path[(j + 1) % pathLength];

                    //Compute the sum of the old edge distances.
                    int oldDistance = Utils.distance(e1u, e1v, distances, pathLength) + Utils.distance(e2u, e2v, distances, pathLength);

                    //Comptue the sum of the edge distances if the other combination of the vertices is used.
                    int newDistance = Utils.distance(e1u, e2u, distances, pathLength) + Utils.distance(e1v, e2v, distances, pathLength);

                    //Compute the improvement.
                    int newGain = newDistance - oldDistance;
                    if(SWAP_ASAP && newGain < 0){
                        best[0] = i;
                        best[1] = j;
                        gain = newGain;
                        swap(path, best);
                        break;
                    }else if (newGain < gain) {
                        //We have improved the path.
                        //Save the edges and the gain, because there could be a better gain.
                        gain = newGain;
                        best[0] = i;
                        best[1] = j;

                        if (System.currentTimeMillis() > endTime) {
                            abortLoop = true;
                            break;
                        }
                    }
                }
            }

            if (gain < 0) {
                //Swap.
                swap(path, best);
            }
        }
    }

    private static void swap(Vertex[] path, int[] best) {
        int swapSize = best[1] - best[0];
        //                if (swapSize < path.length / 2) { TODO reverse the "other" part of path if that part is smaller
        Vertex[] swapArrayPart = new Vertex[swapSize];
        System.arraycopy(path, best[0] + 1, swapArrayPart, 0, swapArrayPart.length);
        for (int i = 0; i < swapSize; i++) {
            path[best[0] + 1 + i] = swapArrayPart[swapArrayPart.length - 1 - i];
        }
        //                System.out.println(Utils.pathDistance(path));
    }
}
