package pers.hywel.algorithm.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix【Medium】
 *
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * 矩阵从外圈到内圈输出
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * @Date 2022-01-04
 */
public class SpiralMatrix {
    /**
     * 四个指针，上下左右作为边界
     * 并且每条边更新完成以后，需要判断是否应该提前结束
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0, left = 0;
        int bottom = matrix.length - 1 , right = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>(matrix.length * 2);
        while (left <= right) {
            // -->
            for (int i = left; i <= right; i++) {
                result.add(matrix[left][i]);
            }
            top++;
            // 往下
            if (top > bottom) break;
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            if ( right < left) break;
            for (int i = right; i >= left; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;
            if (bottom < top) break;
            for (int i = bottom; i >= top; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }

    public static void main(String[] args) {
        SpiralMatrix testObj = new SpiralMatrix();
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(testObj.spiralOrder(matrix));
    }
}
