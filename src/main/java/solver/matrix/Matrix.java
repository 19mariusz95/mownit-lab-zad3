package solver.matrix;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import graph.Vertex;
import solver.cycle.Cycle;
import voltage.Voltage;

import java.util.List;
import java.util.Set;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class Matrix<V extends Vertex, E extends Edge> {
    private double[][] matrixA;
    private double[][] matrixB;
    private Graph<V, E> graph;
    private Set<Cycle<V>> cycles;
    private Voltage<V, E> voltage;

    public Matrix(Graph<V, E> graph, Set<Cycle<V>> cycles, Voltage<V, E> voltage) {
        this.graph = graph;
        this.cycles = cycles;
        this.voltage = voltage;

    }

    public double[][] getMatrixB() {
        return matrixB;
    }

    public double[][] getMatrixA() {
        return matrixA;
    }

    public void createMatrix() {

        int columns = Edge.nextID;
        int rows = graph.getVertexCount() + cycles.size();

        matrixA = new double[rows][columns];
        matrixB = new double[rows][1];
        resetMatrix(matrixA, rows, columns);

        fillMatrix(matrixA, columns);

    }

    private void fillMatrix(double[][] matrix, int columns) {
        int row = 0;
        row = fillWithCycles(matrix, columns, row);
        fillWithVertices(matrix, row);
    }

    private void fillWithVertices(double[][] matrix, int row) {
        for (V vertex : graph.getVertices()) {
            for (V nb : graph.getNeighbors(vertex)) {
                E edge = graph.findEdge(vertex, nb);
                if (edge.getU1id() == vertex.getId())
                    matrix[row][edge.getId()] = 1;
                else
                    matrix[row][edge.getId()] = -1;
            }
            row++;
        }
    }

    private int fillWithCycles(double[][] matrix, int columns, int row) {
        for (Cycle<V> cycle : cycles) {
            List<V> list = cycle.getVertexList();
            for (int i = 0; i < list.size() - 1; i++) {
                V u1 = list.get(i);
                V u2 = list.get(i + 1);
                E edge = graph.findEdge(u1, u2);
                if (edge.getU1id() == u1.getId()) {
                    matrix[row][edge.getId()] = edge.getResistance();
                } else
                    matrix[row][edge.getId()] = -edge.getResistance();
            }
            matrixB[row][0] = voltage.getValue();
            row++;
        }
        return row;
    }

    private void resetMatrix(double[][] matrix, int rows, int columns) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = 0.0;
    }
}
