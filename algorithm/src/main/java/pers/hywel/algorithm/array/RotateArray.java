/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.array;

/**
 * Description:
 *  Rotate Array
 *
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * @author zhangwei111
 * Created on 2019-11-27 14:48
 */
public class RotateArray {
    /**
     * Solution：翻转
     *  例：1 2 3 4 5 6 7 k=2
     *  step 1： 全部翻转==> 7 6 5 4 3 2 1
     *  step 2:  前k翻转==> 6 7 5 4 3 2 1
     *  step 3： 剩下翻转==> 6 7 1 2 3 4 5
     * @param nums
     * @param k
     */
    private void rotate(int[] nums, int k) {
       k %= nums.length;
       reverse(nums, 0, nums.length-1);
       reverse(nums, 0, k-1);
       reverse(nums, k, nums.length-1);
    }

    /**
     * 反转函数
     * @param nums
     * @param begin
     * @param end
     */
    private void reverse(int[] nums, int begin, int end) {
        while(begin < end) {
            int swap = nums[begin];
            nums[begin] = nums[end];
            nums[end] = swap;
            begin++;
            end--;
        }
    }


    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        int[] test = {1, 2, 3, 4 , 5, 6, 7};
        rotateArray.rotate(test, 3);
        for (int i : test) {
            System.out.print(i + " ");
        }
    }
}

