
package pers.hywel.algorithm.integer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:
 * 4 sum
 * <p>
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b
 * + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author RobertZhangwei
 *

 */
public class FourSum {

    /**
     * method by discuss
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 4)
            return res;

        Arrays.sort(nums);

        int max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target)
            return res;

        int i, z;
        for (i = 0; i < len; i++) {
            z = nums[i];
            if (i > 0 && z == nums[i - 1])// avoid duplicate
                continue;
            if (z + 3 * max < target) // z is too small
                continue;
            if (4 * z > target) // z is too large
                break;
            if (4 * z == target) { // z is the boundary
                if (i + 3 < len && nums[i + 3] == z)
                    res.add(Arrays.asList(z, z, z, z));
                break;
            }

            threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
        }

        return res;
    }

    /*
     * Find all possible distinguished three numbers adding up to the target
     * in sorted array nums[] between indices low and high. If there are,
     * add all of them into the ArrayList fourSumList, using
     * fourSumList.add(Arrays.asList(z1, the three numbers))
     */
    public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
                                   int z1) {
        if (low + 1 >= high)
            return;

        int max = nums[high];
        if (3 * nums[low] > target || 3 * max < target)
            return;

        int i, z;
        for (i = low; i < high - 1; i++) {
            z = nums[i];
            if (i > low && z == nums[i - 1]) // avoid duplicate
                continue;
            if (z + 2 * max < target) // z is too small
                continue;

            if (3 * z > target) // z is too large
                break;

            if (3 * z == target) { // z is the boundary
                if (i + 1 < high && nums[i + 2] == z)
                    fourSumList.add(Arrays.asList(z1, z, z, z));
                break;
            }

            twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
        }

    }

    /*
     * Find all possible distinguished two numbers adding up to the target
     * in sorted array nums[] between indices low and high. If there are,
     * add all of them into the ArrayList fourSumList, using
     * fourSumList.add(Arrays.asList(z1, z2, the two numbers))
     */
    public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
                                 int z1, int z2) {

        if (low >= high)
            return;

        if (2 * nums[low] > target || 2 * nums[high] < target)
            return;

        int i = low, j = high, sum, x;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

                x = nums[i];
                while (++i < j && x == nums[i]) // avoid duplicate
                    ;
                x = nums[j];
                while (i < --j && x == nums[j]) // avoid duplicate
                    ;
            }
            if (sum < target)
                i++;
            if (sum > target)
                j--;
        }
        return;
    }

    /**
     * myself method
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSumByMyself(int[] nums, int target) {
        if (nums.length < 4)
            return Collections.emptyList();
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int p = 0; p < nums.length - 3; p++) {
            if (p == 0 || nums[p] != nums[p-1]) {
                List<List<Integer>> threeSumResult = threeSum(nums, target - nums[p], p + 1, nums.length - 1);
                if (!threeSumResult.isEmpty()) {
                    for (List<Integer> list : threeSumResult) {
                        list.add(nums[p]);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    // 已排序nums, 包含left和right
    private List<List<Integer>> threeSum(int[] nums, int target, int left, int right) {
        List<List<Integer>> result = new LinkedList<>();
        for (int p = left; p < right - 1; p++) {
            if (p == left || nums[p] != nums[p-1]) {
                List<List<Integer>> twoSumResult = twoSum(nums, target - nums[p], p + 1, right);
                if (!twoSumResult.isEmpty()) {
                    for (List<Integer> list : twoSumResult) {
                        list.add(nums[p]);
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    // 已排序nums, 包含left和right
    private List<List<Integer>> twoSum(int[] nums, int target, int left, int right) {
        List<List<Integer>> result = new LinkedList<>();
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                result.add(Stream.of(nums[left], nums[right]).collect(Collectors.toList()));
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
                left++;
                right--;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FourSum testClass = new FourSum();
        int[] nums = {0,0,0,0};
        List<List<Integer>> result = testClass.fourSum(nums, 0);
        result.forEach(list -> System.out.println(list.toString()));
    }
}
