
package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 813. Largest Sum of Averages [Medium]
 * We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?
 *
 * Note that our partition must use every number in A, and that scores are not necessarily integers.
 *
 * Example:
 * Input:
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation:
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 *
 * Notes:
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * Answers within 10^-6 of the correct answer will be accepted as correct.
 *
 * @author RobertZhang
 * Created on 2021/3/2 9:57 下午
 */
public class LargestSumOfAverages {
    // 1. dp[i] ： i位置上的最大平均和
    // 2. dp[i] = Math.max(dp[i - K] + avg(i - k, i), dp)
    public double largestSumOfAverages(int[] A, int K) {
        double[] dp = new double[A.length];
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            double sumTemp = 0;
            for (int k = i; k > 0 && k > i - K; k--) {
                sumTemp += A[k];
                dp[i] = Math.max(dp[i], dp[k - 1] + sumTemp / (i - k + 1));
            }
        }
        return dp[A.length - 1];
    }

    public static void main(String[] args) {
        LargestSumOfAverages test = new LargestSumOfAverages();
        int[] A = new int[]{9,1,2,3,9};
        double result = test.largestSumOfAverages(A, 3);
        System.out.println(result);
    }
}
