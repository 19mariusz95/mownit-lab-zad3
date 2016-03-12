package solver;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import graph.Vertex;
import voltage.Voltage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class VoltageSolver<V extends Vertex, E extends Edge> {
    Voltage<V> voltage;
    private Graph<V, E> graph;

    public VoltageSolver(Graph<V, E> graph, Voltage<V> voltage) {
        this.graph = graph;
        this.voltage = voltage;
    }

    public Map<E, Double> solve() {
        //TODO algo
        Map<E, Double> map = new HashMap<>();
        for (E e : graph.getEdges()) {
            map.put(e, e.getResistance());
        }
        return map;
    }
}
