import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import graph.Edge;
import graph.Vertex;
import parser.InputFileParser;
import solver.VoltageSolver;
import solver.cycle.Cycle;
import solver.cycle.CycleDetector;
import voltage.Voltage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class MainClass {
    private static Logger logger = Logger.getLogger(MainClass.class.getName());

    public static void main(String[] args) {
        InputFileParser<Vertex, Edge> inputFileParser = null;
        try {
            File file = new File(args[0]);
            inputFileParser = new InputFileParser<>(Vertex::new, Edge::new, file);
            inputFileParser.parseInput();
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException e) {
            logger.warning(e.getMessage());
            logger.info("You have to specify filename as argument");
            System.exit(1);
        }
        Graph<Vertex, Edge> graph = inputFileParser.getGraph();
        Voltage<Vertex, Edge> voltage = inputFileParser.getVoltage();
        voltage.getV().setColor(Color.red);
        voltage.getU().setColor(Color.red);

        CycleDetector<Vertex, Edge> cycleDetector = new CycleDetector<>(graph);

        Set<Cycle<Vertex>> cycles = cycleDetector.getSetOfCycles(voltage);

        System.out.println(cycles);

        VoltageSolver<Vertex, Edge> voltageSolver = new VoltageSolver<>(graph, cycles, voltage);
        Map<Edge, Double> current = voltageSolver.solve();

        BasicVisualizationServer<Vertex, Edge> basicVisualizationServer = getVisualizationServer(graph, current);

        initFrame(basicVisualizationServer);
    }

    private static void initFrame(BasicVisualizationServer<Vertex, Edge> basicVisualizationServer) {
        JFrame frame = new JFrame("graph");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(basicVisualizationServer);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static BasicVisualizationServer<Vertex, Edge> getVisualizationServer(Graph<Vertex, Edge> graph, Map<Edge, Double> current) {
        Layout<Vertex, Edge> circleLayout = new FRLayout<>(graph);
        BasicVisualizationServer<Vertex, Edge> basicVisualizationServer = new BasicVisualizationServer<>(circleLayout);
        initVisualizationServer(current, basicVisualizationServer);
        return basicVisualizationServer;
    }

    private static void initVisualizationServer(Map<Edge, Double> current, BasicVisualizationServer<Vertex, Edge> basicVisualizationServer) {
        DecimalFormat df = new DecimalFormat("#.##");
        basicVisualizationServer.getRenderContext().setEdgeLabelTransformer(edge -> df.format(current.get(edge)) + " V");
        basicVisualizationServer.getRenderContext().setVertexLabelTransformer(vertex -> String.valueOf(vertex.getId()));
        basicVisualizationServer.getRenderContext().setVertexFillPaintTransformer(Vertex::getColor);
        basicVisualizationServer.setPreferredSize(new Dimension(800, 800));
    }
}
