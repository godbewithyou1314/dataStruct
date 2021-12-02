
package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 1314. Matrix Block Sum 【Medium】
 * Given a m * n matrix mat and an integer K,
 * return a matrix answer where each answer[i][j] is the sum
 * of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K,
 * and (r, c) is a valid position in the matrix.
 * （answer[i][j]为（i,j）位置，周边在K范围内的mat数字的和）
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 * Example 2:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, K <= 100
 * 1 <= mat[i][j] <= 100
 *
 * @author RobertZhang
 * Created on 2021/2/28 11:44 下午
 */
public class MatrixBlockSum {
    /**
     * 标准解法：
     * @param mat
     * @param K
     * @return
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1]; // sum[i][j] is sum of all elements from rectangle (0,0,i,j) as left, top, right, bottom corresponding
        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                sum[r][c] = mat[r - 1][c - 1] + sum[r - 1][c] + sum[r][c - 1] - sum[r - 1][c - 1];
            }
        }
        int[][] ans = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int r1 = Math.max(0, r - K), c1 = Math.max(0, c - K);
                int r2 = Math.min(m - 1, r + K), c2 = Math.min(n - 1, c + K);
                r1++; c1++; r2++; c2++; // Since `sum` start with 1 so we need to increase r1, c1, r2, c2 by 1
                ans[r][c] = sum[r2][c2] - sum[r2][c1-1] - sum[r1-1][c2] + sum[r1-1][c1-1];
            }
        }
        return ans;
    }

    // 同一行：
    // dp[i][j] = dp[i][j - 1] - sum { (j - 1 - K)列, for row in  (i - k , i + k)}
    //              + sum{ j + K 列，for row in  (i - k , i + k) }
    // 换行时：
    // dp[i][0] = dp[i - 1][0] - sum { (i - 1 - K)行, for col in (0, K)}
    //              + sum { (i + K)行, for col in (0, K)}
    public int[][] matrixBlockSumMyself(int[][] mat, int K) {
        int rows = mat.length;
        int cols = mat[0].length;
        int maxRowIndex = Math.min(K, rows - 1);
        int maxColIndex = Math.min(K, cols - 1);
        int[][] answer = new int[rows][cols];
        for (int i = 0; i <= maxRowIndex; i++) {
            for (int j = 0; j <= maxColIndex; j++) {
                answer[0][0] += mat[i][j];
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == 0 && col == 0) continue;
                int minus = 0;
                int append = 0;
                if (col == 0) {
                    if (row - 1 - K >= 0) {
                        for (int k = 0; k <= maxColIndex; k++) {
                            minus += mat[row - 1 - K][k];
                        }
                    }
                    if (row + K < rows) {
                        for (int k = 0; k <= maxColIndex; k++) {
                            append += mat[row + K][k];
                        }
                    }
                    answer[row][0] = answer[row - 1][0] - minus + append;
                } else {
                    if (col - 1 - K >= 0) {
                        for (int k = (Math.max(row - K, 0));  k <= Math.min(row + K, rows - 1); k++) {
                            minus += mat[k][col - 1 -K];
                        }
                    }
                    if (col + K < cols) {
                        for (int k = (Math.max(row - K, 0)); k <= Math.min(row + K, rows - 1); k++) {
                            append += mat[k][col + K];
                        }
                    }
                    answer[row][col] = answer[row][col - 1] - minus + append;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        MatrixBlockSum test = new MatrixBlockSum();
        int[][] mat = new int[][]{{67,64,78},{99,98,38},{82,46,46},{6,52,55},{55,99,45}};
        int[][] answer = test.matrixBlockSumMyself(mat, 3);
        for (int row = 0; row < answer.length; row++) {
            for (int num : answer[row]) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

}
