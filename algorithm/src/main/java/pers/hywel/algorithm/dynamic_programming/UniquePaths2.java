
package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 63. Unique Paths II
 *
 * Medium
 *
 * 见题目： UniquePaths,本题为升级版，增加障碍
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 *
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 *
 * Input:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * @author RobertZhang
 * Created on 2020-05-15
 */
public class UniquePaths2 {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, column = obstacleGrid[0].length;
        int[][] dp = new int[rows][column];
        int initStep = 1;
        for (int i = 0; i < rows; i++) {
            if (obstacleGrid[i][0] == 1) {
                initStep = 0;
            }
            dp[i][0] = initStep;
        }
        initStep = 1;
        for (int j = 0; j < column; j++) {
            if (obstacleGrid[0][j] == 1) {
                initStep = 0;
            }
            dp[0][j] = initStep;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < column; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[rows - 1][column - 1];
    }
}
