package threads.future;

import java.util.concurrent.CompletableFuture;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = getSums(matrix, i);
        }
        return sums;
    }

    private static Sums getSums(int[][] matrix, int i) {
        Sums sum = new Sums();
        for (int j = 0; j < matrix.length; j++) {
            sum.rowSum += matrix[i][j];
            sum.colSum += matrix[j][i];
        }
        return sum;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int finalI = i;
            CompletableFuture.runAsync(
                    () -> {
                        sums[finalI] = getSums(matrix, finalI);
                    }
            );
        }
        return sums;
    }
}
