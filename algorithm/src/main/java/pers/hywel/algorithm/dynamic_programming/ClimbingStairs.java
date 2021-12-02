package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 爬梯子，每次可以走一步或者两部，求解有多少种方式能走到n层
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * solve:
 *  s(n) = s(n-1) +  s(n-2)
 *  第n楼方法，从n-1走一步 和 从n-2楼走两步 两种方式
 *
 * @author RobertZhang
 *

 */
public class ClimbingStairs {
    public static int climbStairs(int n) {
        if (n==0 || n == 1) {
            return 1;
        }
        int[] solves = new int[n+1];
        solves[1] = 1;
        solves[2] = 2;
        for (int i=3; i<=n; i++) {
            solves[i] = solves[i-1] + solves[i-2];
        }
        return solves[n];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
    }
}
