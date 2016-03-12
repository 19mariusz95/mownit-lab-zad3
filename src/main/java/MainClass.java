import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import graph.Edge;
import graph.Vertex;
import parser.InputFileParser;
import solver.VoltageSolver;
import voltage.Voltage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class MainClass {
    public static Logger logger = Logger.getLogger(MainClass.class.getName());

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
        Voltage<Vertex> voltage = inputFileParser.getVoltage();
        VoltageSolver<Vertex, Edge> voltageSolver = new VoltageSolver<>(graph, voltage);
        Map<Edge, Double> current = voltageSolver.solve();
        Layout<Vertex, Edge> circleLayout = new CircleLayout<>(graph);
        BasicVisualizationServer<Vertex, Edge> basicVisualizationServer = new BasicVisualizationServer<>(circleLayout);
        basicVisualizationServer.getRenderContext().setEdgeLabelTransformer(edge -> current.get(edge).toString() + " V");
        basicVisualizationServer.getRenderContext().setVertexLabelTransformer(vertex -> String.valueOf(vertex.getId()));
        basicVisualizationServer.setPreferredSize(new Dimension(800, 800));

        JFrame frame = new JFrame("graph");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(basicVisualizationServer);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
