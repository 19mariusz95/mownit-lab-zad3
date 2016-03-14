package solver.equation;

import solver.matrix.Matrix;

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

        int columns = matrix.getColumns();
        int n = columns - 1;

        //TODO choose which rows can be removed

        for (int i = 0; i < n; i++) {
            double maxEl = abs(matrix.getMatrix()[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (abs(matrix.getMatrix()[k][i]) > maxEl) {
                    maxEl = abs(matrix.getMatrix()[k][i]);
                    maxRow = k;
                }
            }

            for (int k = i; k < n + 1; k++) {
                double tmp = matrix.getMatrix()[maxRow][k];
                matrix.getMatrix()[maxRow][k] = matrix.getMatrix()[i][k];
                matrix.getMatrix()[i][k] = tmp;
            }

            for (int k = i + 1; k < n; k++) {
                double c = -matrix.getMatrix()[k][i] / matrix.getMatrix()[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        matrix.getMatrix()[k][j] = 0;
                    } else {
                        matrix.getMatrix()[k][j] += c * matrix.getMatrix()[i][j];
                    }
                }
            }
        }

        double[] x = getResults(n);

        return x;
    }

    private double[] getResults(int n) {
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = matrix.getMatrix()[i][n] / matrix.getMatrix()[i][i];
            for (int k = i - 1; k >= 0; k--) {
                matrix.getMatrix()[k][n] -= matrix.getMatrix()[k][i] * x[i];
            }
        }
        return x;
    }
}
