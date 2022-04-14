package pers.hywel.algorithm.dynamic_programming;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence [Medium]
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements.
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        // 以i结尾的最大子串长度
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, 1);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            // 前一个数
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j + 1] + 1);
                }
            }
        }
        int longest = 0;
        for(int count : dp)
            longest = Math.max(longest, count);
        return longest;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence testObj = new LongestIncreasingSubsequence();
        int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
        int result = testObj.lengthOfLIS(nums);
        System.out.println(result);
    }
}
