package pers.hywel.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 78. Subsets
 * Medium
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 *
 * @author RobbertZhang
 * Created on 2021/3/5 8:38 下午
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 每次填装个数为i个元素的子集
        for (int i = 0; i < nums.length + 1; i++) {
            backtrack(0, i, nums, new ArrayList<>(), result);
        }
        return result;
    }

    /**
     * 寻找个数为subListSize的子集
     *          0
     *        1   2
     *       2 3   3
     *
     * @param first
     * @param subListSize
     * @param nums
     * @param subList
     * @param result
     */
    private void backtrack(int first, int subListSize, int[] nums,
                           List<Integer> subList, List<List<Integer>> result) {
        // 子集长度为subListSize时，添加到结果列表
        if (subList.size() == subListSize) {
            result.add(new ArrayList<>(subList));
            return;
        }
        for (int i = first; i < nums.length; i++) {
            subList.add(nums[i]);
            backtrack(i + 1, subListSize, nums, subList, result);
            subList.remove(subList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets test = new Subsets();
        List<List<Integer>> result = test.subsets(new int[]{1, 2, 3});
        for (List<Integer> subList : result) {
            for (Integer i : subList) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
