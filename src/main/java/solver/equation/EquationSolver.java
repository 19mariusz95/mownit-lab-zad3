package solver.equation;

import solver.matrix.Matrix;
import solver.matrix.MatrixOperations;

import static java.lang.Math.abs;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class EquationSolver {
    private Matrix matrix;

    public EquationSolver(Matrix matrix) {
        this.matrix = matrix;
    }

    public double[] solve() {
        double[][] matrix = prepareMatrix();
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            double maxEl = abs(matrix[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (abs(matrix[k][i]) > maxEl) {
                    maxEl = abs(matrix[k][i]);
                    maxRow = k;
                }
            }

            for (int k = i; k < n + 1; k++) {
                double tmp = matrix[maxRow][k];
                matrix[maxRow][k] = matrix[i][k];
                matrix[i][k] = tmp;
            }

            for (int k = i + 1; k < n; k++) {
                double c = -matrix[k][i] / matrix[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        matrix[k][j] = 0;
                    } else {
                        matrix[k][j] += c * matrix[i][j];
                    }
                }
            }
        }

        return getResults(matrix, n);
    }

    private double[][] prepareMatrix() {
        double[][] matA = matrix.getMatrixA();
        double[][] matB = matrix.getMatrixB();

        double[][] matAT = MatrixOperations.getTranspose(matA);

        double[][] A = MatrixOperations.getMultiplication(matAT, matA);
        double[][] B = MatrixOperations.getMultiplication(matAT, matB);

        return MatrixOperations.getConcatenate(A, B);
    }

    private double[] getResults(double[][] matrix, int n) {
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = matrix[i][n] / matrix[i][i];
            for (int k = i - 1; k >= 0; k--) {
                matrix[k][n] -= matrix[k][i] * x[i];
            }
        }
        return x;
    }
}
