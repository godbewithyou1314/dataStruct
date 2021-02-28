/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.dynamicprogramming;

/**
 * Description:
 * 1594. Maximum Non Negative Product in a Matrix
 * Medium
 * You are given a rows x cols matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.
 *
 * Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (rows - 1, cols - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.
 *
 * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative return -1.
 *
 * Notice that the modulo is performed after getting the maximum product.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[-1,-2,-3],
 *                [-2,-3,-3],
 *                [-3,-3,-2]]
 * Output: -1
 *
 * Example 2:
 *
 * Input: grid = [[1,-2,1],
 *                [1,-2,1],
 *                [3,-4,1]]
 * Output: 8
 * Explanation: Maximum non-negative product is in bold (1 * 1 * -2 * -4 * 1 = 8).
 *
 * Explanation: It's not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
 *
 * @author RobertZhang
 * Created on 2021/2/26 7:15 下午
 */
public class MaximumNonNegativeProductInAMatrix {
    public int maxProductPath(int[][] grid) {
        int rowNums = grid.length, colNums = grid[0].length, mod = 1_000_000_007;
        // dp[i][j][0]表示(i,j)位置乘积的最大值, dp[i][j][1]表示(i,j)位置的最小值
        long dp[][][] = new long[rowNums][colNums][2];
        dp[0][0] = new long[]{grid[0][0], grid[0][0]};
        for (int i = 0; i < rowNums; i++) {
            for (int j = 0; j < colNums; j++) {
                if (i == 0 && j == 0) continue;
                long a = 0, b = 0;
                // 初始化第一行
                if (i == 0) {
                    dp[i][j][0] = dp[i][j][1] = grid[i][j] * dp[i][j - 1][0];
                } else if (j == 0) {
                    // 初始化第一列
                    dp[i][j][0] = dp[i][j][1] = grid[i][j] * dp[i - 1][j][0];
                } else {
                    a = grid[i][j] * Math.max(dp[i][j - 1][0], dp[i - 1][j][0]);
                    b = grid[i][j] * Math.min(dp[i][j - 1][1], dp[i - 1][j][1]);
                    dp[i][j][0] = Math.max(a, b);
                    dp[i][j][1] = Math.min(a, b);
                }
            }
        }
        if (dp[rowNums - 1][colNums - 1][0] < 0) return -1;
        return (int) ((dp[rowNums - 1][colNums - 1][0]) % mod);
    }

    public static void main(String[] args) {
        MaximumNonNegativeProductInAMatrix test = new MaximumNonNegativeProductInAMatrix();
        int[][] grid = new int[][]{{2,1,3,0,-3,3,-4,4,0,-4},{-4,-3,2,2,3,-3,1,-1,1,-2},{-2,0,-4,2,4,-3,-4,-1,3,4},
                {-1,0,1,0,-3,3,-2,-3,1,0},{0,-1,-2,0,-3,-4,0,3,-2,-2},{-4,-2,0,-1,0,-3,0,4,0,-3},
                {-3,-4,2,1,0,-4,2,-4,-1,-3},{3,-2,0,-4,1,0,1,-3,-1,-1},{3,-4,0,2,0,-2,2,-4,-2,4},{0,4,0,-3,-4,3,3,-1,-2,-2}};
        System.out.println(test.maxProductPath(grid));
    }
}
