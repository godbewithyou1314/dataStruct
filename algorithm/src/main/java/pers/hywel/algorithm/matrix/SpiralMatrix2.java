package pers.hywel.algorithm.matrix;

/**
 * 59. Spiral Matrix II 【Medium】
 *
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n^2 in spiral order.
 *
 * Example 1:
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 *
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 *
 * Constraints:
 * 1 <= n <= 20
 *
 * @Date 2021-12-30
 */
public class SpiralMatrix2 {
    /**
     * matrix循环遍历方式
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0, top = 0;
        int right = n - 1, down = n - 1;
        int count = 1;
        while (left <= right) {
            // -->
            for (int col = left; col <= right; col++) {
                matrix[top][col] = count++;
            }
            top++;
            //  |
            //  v
            for (int row = top; row <= down; row++) {
                matrix[row][right] = count++;
            }
            right--;
            // <--
            for (int col = right; col >= left; col--) {
                matrix[down][col] = count++;
            }
            down--;
            //  ^
            //  |
            for (int row = down; row >= top; row--) {
                matrix[row][left] = count++;
            }
            left++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        SpiralMatrix2 testObj = new SpiralMatrix2();

        int[][] matrix = testObj.generateMatrix(3);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
