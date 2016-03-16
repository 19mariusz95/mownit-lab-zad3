package solver.cycle;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import graph.Vertex;
import voltage.Voltage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class CycleDetector<V extends Vertex, E extends Edge> {
    private Graph<V, E> graph;
    private Set<Cycle<V>> cycles;
    private Map<V, Boolean> visited;

    public CycleDetector(Graph<V, E> graph) {
        this.graph = graph;
        this.visited = new HashMap<>();
    }

    public Set<Cycle<V>> getSetOfCycles(Voltage<V, E> voltage) {
        cycles = new HashSet<>();
        V start = voltage.getU();
        visited.put(start, true);
        V next = voltage.getV();
        visited.put(next, true);
        Cycle<V> c = new Cycle<>();
        c.addVertex(voltage.getU());
        c.addVertex(voltage.getV());
        graph.getNeighbors(next).stream().filter(nb -> !visited.getOrDefault(nb, false)).forEach(nb -> {
            searchCycle(start, next, nb, c);
        });
        return cycles;
    }

    private void searchCycle(V start, V current, V nb, Cycle<V> c) {
        Cycle<V> cycle = new Cycle<>(c.getVertexList());
        cycle.addVertex(nb);
        DFS(start, nb, cycle);
    }

    private void DFS(V start, V cur, Cycle<V> c) {
        if (cur == start) {
            cycles.add(c);
        }
        if (!visited.getOrDefault(cur, false)) {
            visited.put(cur, true);
            for (V nb : graph.getNeighbors(cur)) {
                searchCycle(start, cur, nb, c);

            }
            visited.put(cur, false);
        }
    }


}
