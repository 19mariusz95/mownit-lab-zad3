package solver.equation;

import solver.matrix.Matrix;

/**
 * Created by Mariusz on 12.03.2016.
 */
public class EquationSolver {
    private Matrix matrix;

    public EquationSolver(Matrix matrix) {
        this.matrix = matrix;
    }

    public double[] solve() {
        Jama.Matrix matrix = new Jama.Matrix(this.matrix.getMatrixA());
        Jama.Matrix matrix1 = new Jama.Matrix(this.matrix.getMatrixB());

        return getResult(matrix.solve(matrix1));
    }

    private double[] getResult(Jama.Matrix res) {
        double[] result = new double[res.getRowDimension()];
        for (int i = 0; i < res.getRowDimension(); i++) {
            result[i] = res.get(i, 0);
        }
        return result;
    }
}
