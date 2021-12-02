
package pers.hywel.algorithm.space_reuse;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * 73. Set Matrix Zeroes
 * Medium
 *
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Follow up:
 *
 * 1. A straight forward solution using O(mn) space is probably a bad idea.
 * 2. A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 * Example 1:
 *
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * @author RobertZhang
 * Created on 2021/3/30 2:38 下午
 */
public class SetMatrixZeroes {
    // 空间复杂度：O(1)方案
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 单独用来记录第一列的最终状态，遍历数组列时，每次从第1列开始遍历
        int col0 = 1;
        for (int i = 0; i < m; i++) {
            // 是否第0列需要更新为0
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 从后往前遍历，避免污染未使用数据
        // 遍历 (row,0)第0列，最后处理第一行,避免把第一行存储的列数据污染
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // 如果第0列应该为0，更新当前行的首列为0
            if (col0 == 0) matrix[i][0] = 0;
        }
    }

    // 空间复杂度：O(m + n) 方案
    public void setZeroesNotBest(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        row.forEach(i -> {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;
            }
        });
        col.forEach(j -> {
            for (int i = 0; i < m; i++) {
                matrix[i][j] = 0;
            }
        });
    }

    public static void main(String[] args) {
        SetMatrixZeroes test = new SetMatrixZeroes();
        int[][] matrix = {{1,2,3,4},{5,0,7,8},{0,10,11,12},{13,14,15,0}};
        test.setZeroes(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
