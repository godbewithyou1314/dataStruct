package pers.hywel.algorithm.bit_operate;

import java.util.HashSet;
import java.util.Set;

/**
 * 260. Single Number III
 *
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once. You can return the answer in any order.
 *
 * Follow up: Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 * Example 2:
 *
 * Input: nums = [-1,0]
 * Output: [-1,0]
 */
public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }

    public int[] singleNumberOld(int[] nums) {
        if (nums.length <= 2) return nums;
        Set<Integer> hashSet = new HashSet<>();
        for (Integer i : nums) {
            if (hashSet.contains(i)) hashSet.remove(i);
            else hashSet.add(i);
        }
        int[] resultArray = new int[2];
        int i = 0;
        for (Integer j : hashSet) {
            resultArray[i++] = j;
        }
        return resultArray;
    }
}
