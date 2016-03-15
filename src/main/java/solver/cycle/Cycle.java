package solver.cycle;

import graph.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariusz on 15.03.2016.
 */
public class Cycle {
    private List<Edge> edgeList;

    public Cycle() {
        this.edgeList = new ArrayList<>();
    }

    public Cycle(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public void addEdge(Edge e) {
        this.edgeList.add(e);
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }
}
