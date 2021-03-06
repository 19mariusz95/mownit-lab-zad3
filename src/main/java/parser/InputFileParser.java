package parser;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import factory.EdgeFactory;
import factory.VertexFactory;
import graph.Edge;
import graph.Vertex;
import voltage.Voltage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class InputFileParser<V extends Vertex, E extends Edge> {
    private Graph<V, E> graph;
    private Voltage<V, E> voltage;
    private VertexFactory<V> vertexFactory;
    private ParseStrategy<V, E> parseStrategy;
    private File file;

    public InputFileParser(VertexFactory<V> vertexFactory, EdgeFactory<E> edgeFactory, File file) {
        this.vertexFactory = vertexFactory;
        this.file = file;
        parseStrategy = (v1, v2, value, graph1) -> {
            E e = edgeFactory.getEdge(value);
            e.setU1id(v1.getId());
            e.setU2id(v2.getId());
            graph1.addEdge(e, v1, v2);
        };
    }

    public Graph<V, E> getGraph() {
        if (graph == null)
            throw new IllegalStateException("You have to parse file");
        return graph;
    }

    public Voltage<V, E> getVoltage() {
        if (voltage == null)
            throw new IllegalStateException("You have to parse file");
        return voltage;
    }

    public void parseInput() throws FileNotFoundException {
        graph = new UndirectedSparseGraph<>();
        Scanner scanner = new Scanner(new FileInputStream(file));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().length() == 0) {
                changeParseStrategyToVoltage();
                continue;
            }
            String[] data = line.split(" ");
            int u1ID = Integer.parseInt(data[0]);
            int u2ID = Integer.parseInt(data[1]);
            double value = Double.parseDouble(data[2]);
            parseStrategy.parseData(getVertex(u1ID), getVertex(u2ID), value, graph);
        }
    }

    private void changeParseStrategyToVoltage() {
        parseStrategy = (v1, v2, value, graph1) -> {
            E e = graph1.findEdge(v1, v2);
            if (e == null)
                throw new IllegalArgumentException("There is no edge " + v1.getId() + " " + v2.getId());
            voltage = new Voltage<>(v1, v2, e, value);
        };
    }

    private V getVertex(int id) {
        Optional<V> optional = graph.getVertices().stream().filter(e -> e.getId() == id).findAny();
        return optional.orElseGet(() -> {
            V vert = vertexFactory.getVertex(id);
            graph.addVertex(vert);
            return vert;
        });
    }
}
