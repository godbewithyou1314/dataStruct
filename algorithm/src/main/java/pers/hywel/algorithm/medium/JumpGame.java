/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.medium;

/**
 * Description:
 * 55. Jump Game
 * Medium
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 *
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
 * impossible to reach the last index.
 *
 * @author RobertZhang
 * Created on 2020-05-25
 */
public class JumpGame {

    // dp[i] = Math.max(dp[i - 1] - 1 , i)
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 1) return true;
        if (nums[0] == 0) return false;
        // dp[i + 1]: 以i结尾时，能达到的最远步长
        int[] dp = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i] - 1, nums[i]);
            if (dp[i + 1] == 0 && i != nums.length - 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,0,4};
        System.out.println(canJump(nums));
    }
}
