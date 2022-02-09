package pers.hywel.algorithm.map_set;

import java.util.HashMap;
import java.util.Map;

/**
 * 532. K-diff Pairs in an Array【Medium】
 * <p>
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * <p>
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * <p>
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * Notice that |val| denotes the absolute value of val.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * Example 3:
 * <p>
 * Input: nums = [1,3,1,5,4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -107 <= nums[i] <= 107
 * 0 <= k <= 107
 *
 * @Date 2022-02-09
 */
public class KdiffPairsInAnArray {
    /**
     * 使用map解决，判断num + k是否在map当中
     */
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }
        // 如果k==0，只要重复的
        if (k == 0) {
            return (int) numMap.entrySet().stream().filter(entry -> entry.getValue() > 1).count();
        } else {
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
                if (numMap.containsKey(entry.getKey() + k)) count++;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 4, 1, 5};
        int k = 2;
        KdiffPairsInAnArray testObj = new KdiffPairsInAnArray();
        System.out.println(testObj.findPairs(nums, k));
    }
}
