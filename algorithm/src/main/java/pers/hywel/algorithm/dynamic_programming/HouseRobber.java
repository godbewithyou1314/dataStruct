package pers.hywel.algorithm.dynamic_programming;

/**
 * Description:
 * 小偷问题
 * 求给定数组最大和（限制：不能两数连续相加）
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
 * of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *
 * Solve：
 * profit[n] = max{profit[n-1], profit[n-2]+nums[n]}
 *
 * @author 张益达
 *
 */
public class HouseRobber {
    public static int rob(int[] nums) {
        if (nums.length < 1)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int[] profit = new int[nums.length];
        profit[0] = nums[0];
        profit[1] = Math.max(nums[0], nums[1]);
        for (int i=2; i<nums.length; i++) {
            profit[i] = Math.max(profit[i-1], profit[i-2]+nums[i]);
        }
        return profit[nums.length-1];
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,4};
        System.out.println(rob(test));
    }
}
