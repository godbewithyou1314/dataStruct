/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.dynamicprogramming;

/**
 * Description:
 * 688. Knight Probability in Chessboard [medium]
 * 已知一个 NxN 的国际象棋棋盘，棋盘的行号和列号都是从 0 开始。即最左上角的格子记为(0, 0)，最右下角的记为(N-1, N-1)。
 * 现有一个 “马”（也译作 “骑士”）位于(r, c)，并打算进行K 次移动。
 * <p>
 * 如下图所示，国际象棋的 “马” 每一步先沿水平或垂直方向移动 2 个格子，然后向与之相垂直的方向再移动 1 个格子，共有 8 个可选的位置。
 * <p>
 * 现在 “马” 每一步都从可选的位置（包括棋盘外部的）中独立随机地选择一个进行移动，直到移动了K次或跳到了棋盘外面。
 * <p>
 * 求移动结束后，“马” 仍留在棋盘上的概率。
 * <p>
 * 示例：
 * <p>
 * 输入: 3, 2, 0, 0
 * 输出: 0.0625
 * 解释:
 * 输入的数据依次为 N, K, r, c
 * 第 1 步时，有且只有 2 种走法令 “马” 可以留在棋盘上（跳到（1,2）或（2,1））。对于以上的两种情况，各自在第2步均有且只有2种走法令 “马” 仍然留在棋盘上。
 * 所以 “马” 在结束后仍在棋盘上的概率为 0.0625。
 * <p>
 * 注意：
 * <p>
 * N 的取值范围为 [1, 25]
 * K 的取值范围为 [0, 100]
 * 开始时，“马” 总是位于棋盘上
 *
 * @author RobertZhang
 * Created on 2021/2/2 8:04 下午
 */
public class KnightProbabilityInChessboard {

    /**
     * 定义：dp[row][col]为经过step步后，走到该位置的概率
     * <p>
     * 每一步，一共存在八种走法 （-2,-1）（-2，1）（2，-1）（2，1）（1，-2）（1，2）（-1，-2）（-1，2）
     * 从上一步到下一步格子的到达概率均为 1 / 8
     * <p>
     * dp[row][col] = sum(dp[preRow][preCol] / 8)
     *
     * @return result = sum（dp[row][col]） 每一个位置的概率只和
     */
    public double knightProbability(int N, int K, int r, int c) {
        double[][] dp = new double[N][N];
        int[] rowGo = new int[]{-2, -2, 2, 2, 1, 1, -1, -1};
        int[] colGo = new int[]{-1, 1, -1, 1, -2, 2, -2, 2};
        dp[r][c] = 1;
        for (; K > 0; K--) {
            double[][] dpNew = new double[N][N];
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    for (int goIndex = 0; goIndex < 8; goIndex++) {
                        int rowArrived = row + rowGo[goIndex];
                        int colArrived = col + colGo[goIndex];
                        // 从(step - 1)步遍历8种可能走法，发现仍在格子里，即更新step步到达格子的概率
                        if (0 <= rowArrived && rowArrived < N
                                && 0 <= colArrived && colArrived < N) {
                            dpNew[rowArrived][colArrived] += dp[row][col] / 8;
                        }
                    }
                }
            }
            dp = dpNew;
        }
        double result = 0;
        for (int row = 0; row < N; row++) {
            for (double eachProbability : dp[row]) {
                result += eachProbability;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        KnightProbabilityInChessboard test = new KnightProbabilityInChessboard();
        double result = test.knightProbability(3, 0, 0, 0);
        System.out.println(result);
    }
}
