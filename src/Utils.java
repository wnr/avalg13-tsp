import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Utils {
    static public class Vertex {
        public float x;
        public float y;

        public Vertex(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Vertex() {
            this(0f, 0f);
        }

        @Override
        public String toString() {
            DecimalFormat df = new DecimalFormat("#.#");
            return "(" + df.format(x) + ", " + df.format(y) + ")";
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Vertex)) return false;

            Vertex v = (Vertex)o;
            return this.x == v.x && this.y == v.y;
        }
    }

    public static final float EPSILON = 0.001f;

    public static float distance(Vertex p1, Vertex p2) {
        double a = Math.pow(p1.x - p2.x, 2);
        double b = Math.pow(p1.y - p2.y, 2);
        return (float)Math.sqrt(a + b);
    }

    public static float pathDistance(Integer[] indices, Vertex[] vertices) {
        float sum = 0f;

        int last = indices[0];
        for(int i = 1; i < indices.length; i++) {
            int current = indices[i];
            sum += distance(vertices[last], vertices[current]);
            last = current;
        }

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

    public static Integer[] parseOutput(String output) {
        String[] lines = output.split(System.getProperty("line.separator"));

        Integer[] vertices = new Integer[lines.length];

        for(int i = 0; i < lines.length; i++) {
            vertices[i] = Integer.parseInt(lines[i]);
        }

        return vertices;
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
