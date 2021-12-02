
package pers.hywel.algorithm.dynamic_programming;

import java.util.Arrays;

/**
 * Description:
 * 377. Combination Sum IV
 * Medium
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 * <p>
 * Example:
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * Note that different sequences are counted as different combinations.
 * <p>
 * Therefore the output is 7.
 * <p>
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 * <p>
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test cases.
 *
 * @author RobertZhang
 * Created on 2021/3/8 8:52 下午
 */
public class CombinationSum4 {

    /**
     * dp解法
     * com[target] = sum{com[target - nums[i]]}, target >= nums[i]
     */
    public int combinationSum4ByDP(int[] nums, int target) {
        // 用于保存已经计算过的组合类型
        int[] records = new int[target + 1];
        Arrays.fill(records, -1);
        records[0] = 1;
        return dpRecord(nums, target, records);
    }

    /**
     * 通过record数组保存已经计算过的值
     */
    private int dpRecord(int[] nums, int target, int[] records) {
        if (records[target] != -1) {
            return records[target];
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += dpRecord(nums, target - num, records);
            }
        }
        records[target] = res;
        return res;
    }

    public static void main(String[] args) {
        CombinationSum4 test = new CombinationSum4();
        int[] nums = new int[]{1, 2, 3};
        int result = test.combinationSum4ByDP(nums, 4);
        System.out.println(result);
    }

}
