package solver;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import graph.Vertex;
import org.junit.Before;
import parser.InputFileParser;
import voltage.Voltage;

import java.io.File;

/**
 * Created by Mariusz on 20.03.2016.
 */
public class BaseTest {
    protected Graph<Vertex, Edge> graph;
    protected Voltage<Vertex, Edge> voltage;

    @Before
    public void setUp() throws Exception {
        Edge.nextID = 0;
        File file = new File(VoltageSolverTest.class.getResource("test.txt").getPath());
        InputFileParser<Vertex, Edge> inputFileParser = new InputFileParser<>(id -> new Vertex(id), r -> new Edge(r), file);
        inputFileParser.parseInput();
        graph = inputFileParser.getGraph();
        voltage = inputFileParser.getVoltage();
    }
}
