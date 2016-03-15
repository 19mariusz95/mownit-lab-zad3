package solver.cycle;

import edu.uci.ics.jung.graph.Graph;
import voltage.Voltage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class CycleDetector<V, E> {
    private Graph<V, E> graph;
    private Set<Cycle<E>> cycles;
    private Map<V, Boolean> visited;

    public CycleDetector(Graph<V, E> graph) {
        this.graph = graph;
        this.visited = new HashMap<>();
    }

    public Set<Cycle<E>> getSetOfCycles(Voltage<V, E> voltage) {
        cycles = new HashSet<>();
        V start = voltage.getU();
        visited.put(start, true);
        V next = voltage.getV();
        visited.put(next, true);
        Cycle<E> c = new Cycle<>();
        c.addEdge(voltage.getEdge());
        graph.getNeighbors(next).stream().filter(nb -> !visited.getOrDefault(nb, false)).forEach(nb -> {
            searchCycle(start, next, nb, c);
        });
        return cycles;
    }

    private void searchCycle(V start, V current, V nb, Cycle<E> c) {
        E edge = graph.findEdge(current, nb);
        Cycle<E> cycle = new Cycle<>(c.getEdgeList());
        cycle.addEdge(edge);
        DFS(start, nb, cycle);
    }

    private void DFS(V start, V cur, Cycle<E> c) {
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
