package core;

public class Naive2Opt {
    public static void run(Vertex[] path, int[] distances) {
        int gain = 0;
        Edge[] best = new Edge[2];

        //Loop until a gain is found.
        while(gain == 0) {
            int pathLength = path.length;

            //Loop through all vertices in path.
            for(int i = 0; i < path.length; i++) {

                //Pick one edge.
                Edge edge1 = new Edge(path[i], path[i+1]);

                //Loop from next free vertex in path to the last free vertex.
                //Free vertex = vertex that is not in edge1.
                for(int j = i + 2; j < pathLength; j++) {
                    if(i == 0 && (j+1) == pathLength) {
                        //edge1 contains the first vertex in path, which is connected to
                        //the last vertex in path, so we cannot use it.
                        continue;
                    }

                    //Pick the second edge.
                    Edge edge2 = new Edge(path[j], path[j+1]);

                    //Compute the sum of the old edge distances.
                    int oldDistance = Utils.distance(edge1.u, edge1.v) + Utils.distance(edge2.u, edge1.v);

                    //Comptue the sum of the edge distances if the other combination of the vertices is used.
                    int newDistance = Utils.distance(edge1.u, edge2.v) + Utils.distance(edge1.v, edge2.u);

                    //Compute the improvement.
                    int newGain = oldDistance - newDistance;

                    if(newGain < gain) {
                        //We have improved the path.
                        //Save the edges and the gain, because there could be a better gain.
                        gain = newGain;
                        best[0] = edge1;
                        best[1] = edge2;
                    }
                }
            }

            if(gain < 0) {
                //Swap.
            }
        }
    }
}
