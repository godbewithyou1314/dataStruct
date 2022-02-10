package pers.hywel.algorithm.map_set;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K【Medium】
 *
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 *
 * @Date 2022-02-10
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        /*
            1. Hashmap<sum[0,i - 1], frequency>
            2. sum[i, j] = sum[0, j] - sum[0, i - 1]    --> sum[0, i - 1] = sum[0, j] - sum[i, j]
                   k           sum      hashmap-key     -->  hashmap-key  =  sum - k
            3. now, we have k and sum.
                  As long as we can find a sum[0, i - 1], we then get a valid subarray
                 which is as long as we have the hashmap-key,  we then get a valid subarray
            4. Why don't map.put(sum[0, i - 1], 1) every time ?
                  if all numbers are positive, this is fine
                  if there exists negative number, there could be preSum frequency > 1
         */
        // Hashmap<sum[0,i - 1], frequency>
        Map<Integer, Integer> zeroToISumMap = new HashMap<>();
        zeroToISumMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // sum[0, j] - k = sum[0, i]
            // ==> k = sum[0, j] - sum[0, i]
            // ==> k = sum[i, j]
            if (zeroToISumMap.containsKey(sum - k)) { // there exist a key, that [hashmap-key  =  sum - k]
                result += zeroToISumMap.get(sum - k);
            }
            zeroToISumMap.put(sum, zeroToISumMap.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK testObj = new SubarraySumEqualsK();
        int[] nums = new int[]{1,2,3};
        int k = 3;
        System.out.println(testObj.subarraySum(nums, k));
    }
}
