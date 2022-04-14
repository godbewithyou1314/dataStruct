package pers.hywel.algorithm.matrix;

/**
 * 74. Search a 2D Matrix【Medium】
 *
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row. 每行的第一个一定大于上一行的全部
 *
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * 从一个已排序（从左到右，从上到下）的矩阵中，检索一个target number
 *
 * @Date 2022-03-21
 */
public class SearchA2DMatrix {
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

    public static void main(String[] args) {
        SearchA2DMatrix testObj = new SearchA2DMatrix();
        int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        boolean result = testObj.searchMatrix(matrix, target);
        System.out.println(result);
    }
}
