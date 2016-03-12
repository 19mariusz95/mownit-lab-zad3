package solver;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import graph.Vertex;
import solver.matrix.Matrix;
import voltage.Voltage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class VoltageSolver<V extends Vertex, E extends Edge> {
    Voltage<V> voltage;
    Set<List<E>> cycles;
    private Graph<V, E> graph;

    public VoltageSolver(Graph<V, E> graph, Set<List<E>> cycles, Voltage<V> voltage) {
        this.graph = graph;
        this.voltage = voltage;
        this.cycles = cycles;
    }

    public Map<E, Double> solve() {

        Matrix<V, E> matrix = new Matrix<>(graph, cycles, voltage);

        matrix.createMatrix();

        matrix.print();

        Map<E, Double> map = new HashMap<>();
        for (E e : graph.getEdges()) {
            map.put(e, e.getResistance());
        }
        return map;
    }
}
