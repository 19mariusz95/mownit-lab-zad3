package graph;

import java.util.Objects;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class Edge {
    private static int nextID = 0;
    private double resistance;
    private int id;
    private int u1id;
    private int u2id;

    public Edge(double resistance) {
        this.resistance = resistance;
        this.id = nextID++;
    }

    public int getU1id() {
        return u1id;
    }

    public void setU1id(int u1id) {
        this.u1id = u1id;
    }

    public int getU2id() {
        return u2id;
    }

    public void setU2id(int u2id) {
        this.u2id = u2id;
    }

    public int getId() {
        return id;
    }

    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return getId() == edge.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
