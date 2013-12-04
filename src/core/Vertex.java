package core;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: lucas
 * Date: 12/4/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vertex {
    public float x;
    public float y;
    public int   index;

    public Vertex(float x, float y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vertex() {
        this(0f, 0f, 0);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "(" + df.format(x) + ", " + df.format(y) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vertex)) { return false; }

        Vertex v = (Vertex) o;
        return this.x == v.x && this.y == v.y;
    }
}
