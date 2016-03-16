package solver.cycle;

import graph.Edge;
import graph.Vertex;
import org.junit.Test;
import solver.VoltageSolverTest;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class CycleDetectorTest extends VoltageSolverTest {


    @Test
    public void testGetListOfCycles() throws Exception {
        CycleDetector<Vertex, Edge> cycleDetector = new CycleDetector<>(graph);

        Set<Cycle<Vertex>> list = cycleDetector.getSetOfCycles(voltage);

        System.out.println(list);
        assertTrue(list.size() == 1);
    }
}