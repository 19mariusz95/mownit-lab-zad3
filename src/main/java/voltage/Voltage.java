package voltage;

import graph.Edge;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class Voltage<Vertex, E extends Edge> {
    Vertex u;
    Vertex v;
    E edge;
    double value;

    public Voltage(Vertex u, Vertex v, E edge, double value) {
        this.u = u;
        this.v = v;
        this.edge = edge;
        this.value = value;
    }

    public E getEdge() {
        return edge;
    }

    public Vertex getU() {
        return u;
    }

    public void setU(Vertex u) {
        this.u = u;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
