package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 1504. Count Submatrices With All Ones
 * Medium
 * Given a rows * columns matrix mat of ones and zeros,
 * return how many submatrices have all ones.
 *
 * Example 1:
 *
 * Input: mat = [[1,0,1],
 *               [1,1,0],
 *               [1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 *
 * @author RobertZhang
 * Created on 2021/3/10 10:57 下午
 */
public class CountSubmatricesWithAllOnes {
    public int numSubmat(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        // 包含(i,j)位置时的1矩阵的个数
        int[][] dp = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

            }
        }
        return 0;
    }
}
