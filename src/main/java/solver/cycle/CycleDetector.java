package solver.cycle;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import graph.Edge;
import voltage.Voltage;

import java.util.*;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class CycleDetector<V, E> {
    private Graph<V, E> graph;
    private Set<List<E>> cycles;
    private Map<V, Boolean> visited;

    public CycleDetector(Graph<V, E> graph) {
        this.graph = graph;
        this.visited = new HashMap<>();
    }

    public Set<List<E>> getSetOfCycles(Voltage<V, Edge> voltage) {
        cycles = new HashSet<>();
        V start = voltage.getU();
        V next = voltage.getV();
        for (E edge : graph.getOutEdges(next)) {
            Pair<V> pair = graph.getEndpoints(edge);
            V nb = pair.getFirst() == next ? pair.getSecond() : pair.getFirst();
            if (!visited.getOrDefault(nb, false)) {
                visited.put(nb, true);
                List<E> set = new ArrayList<>();
                set.add(edge);
                DFS(start, nb, set);
                visited.put(nb, false);
            }
        }
        return cycles;
    }

    private void DFS(V start, V v, List<E> edges) {
        List<E> l = new ArrayList<>(edges);
        if (v == start) {
            cycles.add(l);
        }
        for (E edge : graph.getOutEdges(v)) {
            Pair<V> pair = graph.getEndpoints(edge);
            V nb = pair.getFirst() == v ? pair.getSecond() : pair.getFirst();
            if (!l.contains(edge)) {
                l.add(edge);
                if (!visited.getOrDefault(nb, false)) {
                    visited.put(nb, true);
                    DFS(start, nb, l);
                    visited.put(nb, false);
                }
            }
        }
    }
}
