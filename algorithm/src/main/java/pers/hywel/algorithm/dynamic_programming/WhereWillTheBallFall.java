
package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 1706. Where Will the Ball Fall
 * Medium
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom sides.
 *
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.
 *
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom.
 * A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects the ball into either wall of the box.
 *
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after dropping the ball from the ith column at the top,
 * or -1 if the ball gets stuck in the box.
 *
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * Output: [0,1,2,3,4,-1]
 *
 * @author RobertZhang
 * Created on 2021/2/24 7:28 下午
 */
public class WhereWillTheBallFall {
    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length, res[] = new int[n];
        for (int i = 0; i < n; ++i) {
            int i1 = i, i2;
            for (int j = 0; j < m; ++j) {
                // 1时往右走，正好+1，-1时往左走，正好减-1
                i2 = i1 + grid[j][i1];
                // 如果前后列不一样，无法往下走
                if (i2 < 0 || i2 >= n || grid[j][i2] != grid[j][i1]) {
                    i1 = -1;
                    break;
                }
                i1 = i2;
            }
            res[i] = i1;
        }
        return res;
    }

    public int[] findBallMyself(int[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int[][] dp = new int[rowLength + 1][grid[0].length];
        for (int col = 0; col < colLength; col++) {
            dp[0][col] = col;
        }
        for (int row = 1; row < rowLength + 1; row++) {
            for (int col = 0; col < colLength; col++) {
                // 当前列的球无法已经卡住了
                if (dp[row - 1][col] == -1) {
                    dp[row][col] = -1;
                    continue;
                }
                int curCol = dp[row - 1][col];
                // v字或者，左边缘和右边缘无法下滚的情况
                if ((grid[row - 1][curCol] == 1 && ((curCol == colLength - 1) || (grid[row - 1][curCol + 1] == -1)))
                        || (grid[row - 1][curCol] == -1 && ((curCol == 0) || (grid[row - 1][curCol - 1] == 1)))) {
                    dp[row][col] = -1;
                } else if (grid[row - 1][curCol] == 1) {
                    // 从右边下滚
                    dp[row][col] = dp[row - 1][col] + 1;
                } else if (grid[row - 1][curCol] == -1) {
                    // 从左边下滚
                    dp[row][col] = dp[row - 1][col] - 1;
                }
            }
        }
        return dp[rowLength];
    }

    public static void main(String[] args) {
        WhereWillTheBallFall test = new WhereWillTheBallFall();
        int[][] grid = new int[][]{
                {1,1,1,1,1,1},
                {-1,-1,-1,-1,-1,-1},
                {1,1,1,1,1,1},
                {-1,-1,-1,-1,-1,-1}};
        int[]  result = test.findBall(grid);
        for (int i : result) System.out.println(i);
    }
}
