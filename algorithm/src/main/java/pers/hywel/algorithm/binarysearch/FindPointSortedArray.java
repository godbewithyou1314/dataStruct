
package pers.hywel.algorithm.binarysearch;

/**
 * Description:
 * 从已排序的数组中查找target，第一次和最后一次出现的index
 *
 * 34. Find First and Last Position of Element in Sorted Array
 * Medium
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target
 * value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * @author zhangwei111
 * Created on 2020-03-26 17:05
 */
public class FindPointSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1 , -1};
        if (nums == null || nums.length < 1) return result;
        int midIndex = binarySearch(nums, target);
        if (midIndex < 0) return result;
        // 找第一个
        int start = 0, end = midIndex;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[start] == target) break;
            if (nums[mid] == target) end = mid;
            // eq (nums[mid] < target)
            else start = mid + 1;
        }
        result[0] = start;
        // 找最后一个
        start = midIndex;
        end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2 + 1;
            if (nums[end] == target) break;
            if (nums[mid] == target) start = mid;
            else end = mid - 1;
        }
        result[1] = end;
        return result;
    }

    private int binarySearch(int[] nums, int target) {
       int start = 0, end = nums.length - 1;
       while (start <= end) {
           int mid = start + (end - start) / 2;
           if (target == nums[mid]) return mid;
           if (target < nums[mid]) end = mid - 1;
           else start = mid + 1;
       }
       return -1;
    }

    public static void main(String[] args) {
        FindPointSortedArray testClass = new FindPointSortedArray();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = testClass.searchRange(nums, target);
        System.out.printf("[%s %s]", result[0], result[1]);
    }
}
