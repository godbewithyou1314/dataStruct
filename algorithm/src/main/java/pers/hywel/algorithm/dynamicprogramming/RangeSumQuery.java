/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.dynamicprogramming;

/**
 * Description:
 * [Easy] Range Sum Query - Immutable
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 *
 * @author RobertZhang
 * Created on 2020-03-05
 *
 */
public class RangeSumQuery {

    int[] array;
    int[] sum_1;
    int current = 0;

    public RangeSumQuery(int[] nums, int nothing_just_diff_next_init) {
        array = nums;
        sum_1 = new int[nums.length + 1];
        current = 0;
    }

    public int sumRange_1(int i, int j) {
        // 动态加和，没用到时，不加
        if (current < j ) {
            for (int k = current; k<=j; k++) {
                sum_1[k + 1] = sum_1[k] + array[k];
                current = k;
            }
        }

        return sum_1[j + 1] - sum_1[i];
    }

    /**
     * 方式二
     */
    private int[] sum;

    public RangeSumQuery(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange_2(int i, int j) {
        return sum[j + 1] - sum[i];
    }

}
