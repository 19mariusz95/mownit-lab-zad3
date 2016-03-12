package solver.cycle;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

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

    public Set<List<E>> getSetOfCycles(V start) {
        cycles = new TreeSet<>((Comparator<List<E>>) (o1, o2) -> {
            if (o1.containsAll(o2) && o2.containsAll(o1))
                return 0;
            return 1;
        });
        for (E edge : graph.getOutEdges(start)) {
            Pair<V> pair = graph.getEndpoints(edge);
            V nb = pair.getFirst() == start ? pair.getSecond() : pair.getFirst();
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
        for (E edge : graph.getOutEdges(v)) {
            Pair<V> pair = graph.getEndpoints(edge);
            V nb = pair.getFirst() == v ? pair.getSecond() : pair.getFirst();
            List<E> l = new ArrayList<>(edges);
            if (!l.contains(edge)) {
                l.add(edge);
                if (nb == start) {
                    cycles.add(l);
                } else if (!visited.getOrDefault(nb, false)) {
                    visited.put(nb, true);
                    DFS(start, nb, l);
                    visited.put(nb, false);
                }
            }
        }
    }
}
