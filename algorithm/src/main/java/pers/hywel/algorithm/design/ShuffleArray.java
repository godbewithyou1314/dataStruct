/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.design;

import java.util.Random;

/**
 * Description:
 * 随机打乱一个数组。实现shuffle打乱函数，reset重置函数
 * Shuffle an Array
 * Solution
 * Shuffle a set of numbers without duplicates.
 *
 * Example:
 *
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 *
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 *
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 *
 * @author RobertZhang
 *

 */
public class ShuffleArray {
    int[] current;
    int[] origin;

    public ShuffleArray(int[] nums) {
        origin = new int[nums.length];
        System.arraycopy(nums, 0, origin, 0, nums.length);
        this.current = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Random random = new Random();
        for (int i=0; i<current.length; i++) {
            int r = random.nextInt(current.length-i);
            int swap = current[current.length-1-i];
            current[current.length-1-i] = current[r];
            current[r] = swap;
        }
        return current;
    }
}
