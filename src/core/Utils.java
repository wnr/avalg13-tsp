package core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Utils {

    public static final float EPSILON = 0.001f;

    public static int distance(Vertex p1, Vertex p2) {
        return (int) (Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)) + 0.5);
    }

    public static int distance(Vertex p1, Vertex p2, int[] distanceMatrix, int verticesSize) {
        if (distanceMatrix == null) {
            return distance(p1, p2);
        }
        else {
            return distanceMatrix[p1.index * verticesSize + p2.index];
        }
    }

    public static int pathDistance(Vertex[] vertices) {
        int sum = 0;

        Vertex last = vertices[0];
        for (int i = 1; i < vertices.length; i++) {
            Vertex current = vertices[i];
            sum += distance(last, current);
            last = current;
        }

        //Also add the sum from the end to start path since the salesman needs to return to the starting point.
        sum += distance(vertices[vertices.length - 1], vertices[0]);

        return sum;
    }

    public static Vertex[] parseInput(String input) {
        String[] lines = input.split(System.getProperty("line.separator"));

        int numVertices = Integer.parseInt(lines[0]);
        Vertex[] vertices = new Vertex[numVertices];

        for (int i = 1; i <= numVertices; i++) {
            String[] data = lines[i].split(" ");
            vertices[i - 1] = new Vertex(Float.parseFloat(data[0]), Float.parseFloat(data[1]), i - 1);
        }

        return vertices;
    }

    public static int[] parseOutput(String output) {
        int scale = 0;

        Pattern p = Pattern.compile("^0|(\\n0\\n)|0$");

        if (!p.matcher(output).find()) {
            scale = 1;
        }

        String[] lines = output.split(System.getProperty("line.separator"));

        int[] vertices = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            vertices[i] = Integer.parseInt(lines[i]) - scale;
        }

        return vertices;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public static String pathToOutput(Vertex[] indices) {
        String output = "";

        for (int i = 0; i < indices.length; i++) {
            output += indices[i].index;

            if (i != indices.length - 1) {
                output += "\n";
            }
        }

        return output;
    }

    public static int[] computeDistanceMatrix(Vertex[] inputCoords) {
        int[] distanceMatrix = new int[inputCoords.length * inputCoords.length];
        for (int row = 0; row < inputCoords.length; row++) {
            int i = row * inputCoords.length;
            for (int col = 0; col < inputCoords.length; col++) {
                distanceMatrix[i + col] = distance(inputCoords[row], inputCoords[col]);
            }
        }
        return distanceMatrix;
    }
}
