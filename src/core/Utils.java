package core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Utils {

    public static final float EPSILON = 0.001f;

    public static float distance(Vertex p1, Vertex p2) {
        double a = Math.pow(p1.x - p2.x, 2);
        double b = Math.pow(p1.y - p2.y, 2);
        return (float)Math.sqrt(a + b);
    }

    public static float pathDistance(int[] indices, Vertex[] vertices) {
        float sum = 0f;

        int last = indices[0];
        for(int i = 1; i < indices.length; i++) {
            int current = indices[i];
            sum += distance(vertices[last], vertices[current]);
            last = current;
        }

        //Also add the sum from the end to start path since the salesman needs to return to the starting point.
        sum += distance(vertices[indices[indices.length-1]], vertices[indices[0]]);

        return sum;
    }

    public static Vertex[] parseInput(String input) {
        String[] lines = input.split(System.getProperty("line.separator"));

        int numVertices = Integer.parseInt(lines[0]);
        Vertex[] vertices = new Vertex[numVertices];

        for(int i = 1; i <= numVertices; i++) {
            String[] data = lines[i].split(" ");
            vertices[i-1] = new Vertex(Float.parseFloat(data[0]), Float.parseFloat(data[1]));
        }

        return vertices;
    }

    public static int[] parseOutput(String output) {
        int scale = 0;

        Pattern p = Pattern.compile("^0|(\\n0\\n)|0$");

        if(!p.matcher(output).find()) {
            scale = 1;
        }

        String[] lines = output.split(System.getProperty("line.separator"));

        int[] vertices = new int[lines.length];

        for(int i = 0; i < lines.length; i++) {
            vertices[i] = Integer.parseInt(lines[i]) - scale;
        }

        return vertices;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public static String pathToOutput(int[] indices) {
        String output = "";

        for(int i = 0; i < indices.length; i++) {
            output += indices[i];

            if(i != indices.length-1) {
                output += "\n";
            }
        }

        return output;
    }
}
