package pers.hywel.algorithm.matrix;

/**
 * 240. Search a 2D Matrix II【Medium】
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right. 从左到右升序
 * Integers in each column are sorted in ascending from top to bottom. 从上到下升序
 *
 * Example 1:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 * Example 2:
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 */
public class SearchA2DMatrixII {
    // 从左下角开始检索，构造一个方向大一个方向小
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;
        // 指针[row, col]
        int[] pointer = new int[]{matrix.length - 1, 0};
        while (pointer[0] >= 0 && pointer[0] < matrix.length
                && pointer[1] >= 0 && pointer[1] < matrix[0].length) {
            if (matrix[pointer[0]][pointer[1]] == target) return true;
            else if (matrix[pointer[0]][pointer[1]] < target) pointer[1]++;
            else pointer[0]--;
        }
        return false;
    }
}
