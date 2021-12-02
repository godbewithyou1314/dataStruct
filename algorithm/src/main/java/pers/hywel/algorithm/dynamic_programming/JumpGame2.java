package pers.hywel.algorithm.dynamic_programming;

/**
 * 45. Jump Game II
 * Medium
 *
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 */
public class JumpGame2 {
    public static int jump(int[] nums) {
        // jump次数
        int jumpCount = 0;
        // 每一轮最远能跳到的下标
        int furthestIndex = 0;
        // 当前能达到的最远地
        int curFurthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            // 每一轮里遍历，用贪心算法，找下一轮能到的最远地址
            curFurthest = Math.max(curFurthest, i + nums[i]);
            // 达到当轮能达到的最远距离时，必须有一次jump。并且更新下轮能达到的最远地址
            if (i == furthestIndex) {
                jumpCount++;
                furthestIndex = curFurthest;
            }
        }
        return jumpCount;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
