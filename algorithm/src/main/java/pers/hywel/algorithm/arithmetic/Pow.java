package pers.hywel.algorithm.arithmetic;

/**
 * 50. Pow(x, n) 【Medium】
 * 实现指数运算
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * Example 1:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 * Example 2:
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 *
 * Example 3:
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/22 = 1/4 = 0.25
 *
 * Constraints:
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 *
 * @Date 2022-01-05
 */
public class Pow {
    // x ^ 0 = 1
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0) {
            // 当n = -2,147,483,648时，取反会溢出
//            n = -n;
//            x = 1/x;
            // 避免溢出：整数范围 [-2,147,483,648 , 2,147,483,647]，当n = -2,147,483,648时，取反会溢出
            return 1/x * myPow(1/x , -(n + 1));
        }
        // 如果n为偶数，2 ^ 4 = (2 ^ 2) ^ 2 = 4 ^ 2
        // 如果n为奇数， 2 ^ 3 = 2 * (2 ^ 2) ^ (3 / 2)  = 2^3 ^ 1
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        Pow testObj = new Pow();
        System.out.println(testObj.myPow(2, -2));
    }
}
