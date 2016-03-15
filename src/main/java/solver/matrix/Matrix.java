package solver.matrix;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import solver.cycle.Cycle;
import voltage.Voltage;

import java.util.Set;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class Matrix<V, E extends Edge> {
    private double[][] matrix;
    private Graph<V, E> graph;
    private Set<Cycle<E>> cycles;
    private Voltage<V, E> voltage;
    private int columns;
    private int rows;

    public Matrix(Graph<V, E> graph, Set<Cycle<E>> cycles, Voltage<V, E> voltage) {
        this.graph = graph;
        this.cycles = cycles;
        this.voltage = voltage;

    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void createMatrix() {

        columns = graph.getEdgeCount() + 1;
        rows = graph.getVertexCount() + cycles.size();

        matrix = new double[rows][columns];
        resetMatrix(matrix, rows, columns);

        fillMatrix(matrix, columns);

    }

    private void fillMatrix(double[][] matrix, int columns) {
        int row = 0;
        row = fillWithCycles(matrix, columns, row);
        fillWithVertices(matrix, row);
    }

    private void fillWithVertices(double[][] matrix, int row) {
        for (V vertex : graph.getVertices()) {
            for (E edge : graph.getInEdges(vertex))
                matrix[row][edge.getId()] = 1.0;
            for (E edge : graph.getOutEdges(vertex))
                matrix[row][edge.getId()] = -1.0;
            row++;
        }
    }

    private int fillWithCycles(double[][] matrix, int columns, int row) {
        //TODO
        return -1;
    }

    private void resetMatrix(double[][] matrix, int rows, int columns) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = 0.0;
    }

    public void print() {
        if (matrix == null)
            throw new IllegalStateException("Matrix is not built");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j < columns - 1) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.print("| " + matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
