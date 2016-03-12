package parser;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import graph.Vertex;

/**
 * Created by Mariusz on 12.03.2016.
 */
public interface ParseStrategy<V extends Vertex, E extends Edge> {
    void parseData(V v1, V v2, double value, Graph<V, E> graph);
}
