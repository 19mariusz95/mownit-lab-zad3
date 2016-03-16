package solver.matrix;

public class MatrixOperations {
    public static double[][] getTranspose(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[columns][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                result[j][i] = matrix[i][j];
        return result;
    }

    public static double[][] getMultiplication(double[][] matrixA, double[][] matrixB) {
        int n = matrixA.length;
        int m = matrixA[0].length;
        if (matrixB.length != m)
            throw new IllegalArgumentException();
        int p = matrixB[0].length;
        double[][] result = new double[n][p];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < p; j++) {
                double tmp = 0;
                for (int r = 0; r < m; r++)
                    tmp += matrixA[i][r] * matrixB[r][j];
                result[i][j] = tmp;
            }
        return result;
    }

    public static double[][] getConcatenate(double[][] a, double[][] b) {
        int rows = a.length;
        int ac = a[0].length;
        int bc = b[0].length;
        int columns = ac + bc;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(a[i], 0, result[i], 0, ac);
            System.arraycopy(b[i], 0, result[i], ac, ac + bc - ac);
        }
        return result;
    }
}