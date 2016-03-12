package solver;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import graph.Vertex;
import org.junit.Before;
import org.junit.Test;
import parser.InputFileParser;
import voltage.Voltage;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Created by Mariusz on 12.03.2016.
 */
public class VoltageSolverTest {
    protected Graph<Vertex, Edge> graph;
    protected Voltage<Vertex> voltage;

    @Before
    public void setUp() throws Exception {
        File file = new File(VoltageSolverTest.class.getResource("test.txt").getPath());
        InputFileParser<Vertex, Edge> inputFileParser = new InputFileParser<>(id -> new Vertex(id), r -> new Edge(r), file);
        inputFileParser.parseInput();
        graph = inputFileParser.getGraph();
        voltage = inputFileParser.getVoltage();
    }

    @Test
    public void testSolve() throws Exception {
        VoltageSolver<Vertex, Edge> voltageSolver = new VoltageSolver<>(graph, null, voltage);
        Map<Edge, Double> current = voltageSolver.solve();
        for (Edge e : graph.getEdges()) {
            assertEquals(current.get(e), voltage.getValue() / e.getResistance(), Double.MIN_VALUE);
        }
    }
}