package pers.hywel.algorithm.dynamic_programming;

/**
 * 264. Ugly Number II 【Medium】
 * <p>
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * <p>
 * Given an integer n, return the nth ugly number.
 * <p>
 * Example 1:
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 1690
 */
public class UglyNumber2 {
    /**
     * The idea of this solution is from this page:http://www.geeksforgeeks.org/ugly-numbers/
     * <p>
     * The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
     * because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:
     * 分三组：丑数都是由 【2 * 丑数组合，3 * 丑数集合，5 * 丑数集合】生成而来，每次取一个最小
     * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     * We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.
     *
     */
    public int nthUglyNumber(int n) {
        // dp[n] 表示第n个丑数
        int[] uglyDp = new int[n + 1];
        uglyDp[0] = 0;
        uglyDp[1] = 1;
        int index2 = 1, index3 = 1, index5 = 1;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for (int i = 2; i <= n; i++) {
            int curMinFactor = Math.min(Math.min(factor2, factor3), factor5);
            uglyDp[i] = curMinFactor;
            if (curMinFactor == factor2) {
                factor2 = 2 * uglyDp[++index2];
            }
            // 不能用else if。因为 factor2和factor3存在相同的值，例如，当curMinFactor = 6 时，如果只更新factor2，factor3会造成重复添加
            if (curMinFactor == factor3) {
                factor3 = 3 * uglyDp[++index3];
            }
            if (curMinFactor == factor5) {
                factor5 = 5 * uglyDp[++index5];
            }
        }
        return uglyDp[n];
    }

    public static void main(String[] args) {
        UglyNumber2 testObj = new UglyNumber2();
        System.out.println(testObj.nthUglyNumber(1352));
    }
}
