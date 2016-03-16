package solver.cycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariusz on 15.03.2016.
 */
public class Cycle<V> {
    private List<V> vertexList;

    public Cycle() {
        this.vertexList = new ArrayList<>();
    }

    public Cycle(List<V> vertexList) {
        this.vertexList = new ArrayList<>(vertexList);
    }

    @Override
    public String toString() {
        return vertexList.toString();
    }

    public void addVertex(V e) {
        this.vertexList.add(e);
    }

    public List<V> getVertexList() {
        return vertexList;
    }
}
