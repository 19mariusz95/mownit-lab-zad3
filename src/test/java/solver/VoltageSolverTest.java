package solver;

import graph.Edge;
import graph.Vertex;
import org.junit.Test;
import solver.cycle.CycleDetector;

import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Created by Mariusz on 12.03.2016.
 */
public class VoltageSolverTest extends BaseTest {

    @Test
    public void testSolve() throws Exception {
        CycleDetector<Vertex, Edge> cycleDetector = new CycleDetector<>(graph);
        VoltageSolver<Vertex, Edge> voltageSolver = new VoltageSolver<>(graph, cycleDetector.getSetOfCycles(voltage), voltage);
        Map<Edge, Double> current = voltageSolver.solve();
        final double[] rtmp = {0.0};
        graph.getEdges().stream().forEach(e -> rtmp[0] += e.getResistance());
        for (Edge e : graph.getEdges()) {
            assertEquals("edge: " + e.getId(), voltage.getValue() / rtmp[0], Math.abs(current.get(e)), 0.05);
        }
    }
}