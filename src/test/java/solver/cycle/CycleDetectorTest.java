package solver.cycle;

import graph.Edge;
import graph.Vertex;
import org.junit.Test;
import solver.BaseTest;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class CycleDetectorTest extends BaseTest {


    @Test
    public void testGetListOfCycles() throws Exception {
        CycleDetector<Vertex, Edge> cycleDetector = new CycleDetector<>(graph);

        Set<Cycle<Vertex>> list = cycleDetector.getSetOfCycles(voltage);

        assertTrue(list.size() == 1);
    }
}