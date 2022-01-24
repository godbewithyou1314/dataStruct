package pers.hywel.algorithm.dynamic_programming;

import pers.hywel.algorithm.common.PrintUtils;

import java.util.Date;

/**
 * 263. Ugly Number【Easy】
 *
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 *
 * Example 1:
 * Input: n = 6
 * Output: true
 * Explanation: 6 = 2 × 3
 *
 * Example 2:
 * Input: n = 1
 * Output: true
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 *
 * Example 3:
 * Input: n = 14
 * Output: false
 * Explanation: 14 is not ugly since it includes the prime factor 7.
 *
 * Constraints:
 *
 * -231 <= n <= 231 - 1
 *
 * @Date 2022-01-04 判断是否丑数
 */
public class UglyNumber {
    public static boolean isUgly(int n) {
        int[] divisors = new int[]{2, 3, 5};
        for (int divisor : divisors) {
            while (n > 0 && n % divisor == 0) {
                n /= divisor;
            }
        }
        return n == 1;
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        System.out.println("开始时间：" + startTime);
        int count = 0;
        while (count++ < 10000) if (count == 1) System.out.println(UglyNumber.isUgly(16384));
        Long endTime = System.currentTimeMillis();
        System.out.println("结束时间：" + endTime);
        System.out.println("总耗时(毫秒)：" + (endTime - startTime));
    }
}
