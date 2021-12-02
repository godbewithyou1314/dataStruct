
package pers.hywel.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * 90. Subsets II
 * Medium
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 *
 * @author RobertZhang
 * Created on 2021/3/5 10:27 下午
 */
public class Subsets2 {
    /**
     * 和subsets1相比，区别：给定的元素里存在重复元素，结果里不能重复
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backTracking(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backTracking(int start, int[] nums, List<Integer> curList, List<List<Integer>> result) {
        result.add(new ArrayList<>(curList));
        for (int i = start; i < nums.length; i++) {
            // 跳过重复
            if(i > start && nums[i] == nums[i-1]) continue;
            curList.add(nums[i]);
            backTracking(i + 1, nums, curList, result);
            curList.remove(curList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets2 test = new Subsets2();
        List<List<Integer>> result = test.subsetsWithDup(new int[]{1, 2, 2});
        for (List<Integer> subList : result) {
            for (Integer i : subList) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
