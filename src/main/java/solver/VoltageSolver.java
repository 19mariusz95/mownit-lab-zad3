package solver;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import graph.Edge;
import graph.Vertex;
import solver.cycle.Cycle;
import solver.equation.EquationSolver;
import solver.matrix.Matrix;
import voltage.Voltage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class VoltageSolver<V extends Vertex, E extends Edge> {
    private Voltage<V, E> voltage;
    private Set<Cycle<V>> cycles;
    private Graph<V, E> graph;

    public VoltageSolver(Graph<V, E> graph, Set<Cycle<V>> cycles, Voltage<V, E> voltage) {
        this.graph = graph;
        this.voltage = voltage;
        this.cycles = cycles;
    }

    public Map<E, Double> solve() {

        Matrix<V, E> matrix = new Matrix<>(graph, cycles, voltage);

        matrix.createMatrix();

        EquationSolver equationSolver = new EquationSolver(matrix);

        double[] result = equationSolver.solve();

        for (int i = 0; i < result.length; i++) {
            final int finalI = i;
            E edge = graph.getEdges().stream().filter(f -> f.getId() == finalI).findAny().get();
            Pair<V> endpoints = graph.getEndpoints(edge);
            System.out.println(endpoints.getFirst().getId() + " " + endpoints.getSecond().getId() + "  :  " + result[i]);
        }

        Map<E, Double> map = new HashMap<>();
        for (E e : graph.getEdges()) {
            map.put(e, result[e.getId()]);
        }
        return map;
    }
}
