import java.text.DecimalFormat;

public class Utils {
    static public class Point {
        public float x;
        public float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
            this(0f, 0f);
        }

        @Override
        public String toString() {
            DecimalFormat df = new DecimalFormat("#.#");
            return "(" + df.format(x) + ", " + df.format(y) + ")";
        }
    }

    public static final float EPSILON = 0.001f;

    public static float distance(Point p1, Point p2) {
        double a = Math.pow(p1.x - p2.x, 2);
        double b = Math.pow(p1.y - p2.y, 2);
        return (float)Math.sqrt(a + b);
    }
}
