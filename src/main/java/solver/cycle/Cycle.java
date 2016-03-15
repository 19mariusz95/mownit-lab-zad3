package solver.cycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariusz on 15.03.2016.
 */
public class Cycle<E> {
    private List<E> edgeList;

    public Cycle() {
        this.edgeList = new ArrayList<>();
    }

    public Cycle(List<E> edgeList) {
        this.edgeList = edgeList;
    }

    public void addEdge(E e) {
        this.edgeList.add(e);
    }

    public List<E> getEdgeList() {
        return edgeList;
    }
}
