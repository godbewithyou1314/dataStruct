/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.integer;

import java.util.Arrays;

/**
 * Description:
 * 3 sum closest 相加和与目标值最近的三个数
 * <p>
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest
 * to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * @author RobertZhang
 */
public class ThreeIntSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int closestSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 跳过重复数字
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (Math.abs(sum - target) < diff) {
                        diff = Math.abs(sum - target);
                        closestSum = sum;
                    }
                    // 找到target
                    if (sum == target) {
                        return sum;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        ThreeIntSumClosest testClass = new ThreeIntSumClosest();
        int[] testArray = {-55, -24, -18, -11, -7, -3, 4, 5, 6, 9, 11, 23, 33};
        int target = 0;
        System.out.println(testClass.threeSumClosest(testArray, target));
    }
}
