
package pers.hywel.algorithm.binarysearch;

/**
 * Description:
 * Search in Rotated Sorted Array
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * @author RobertZhang
 */
public class SearchInRotatedSortedArray {

    public int findTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int  minIndex = findMinIndex(nums);
        if (target == nums[minIndex]) return minIndex;
        int last = nums.length - 1;
        int start = target > nums[last] ? 0 : minIndex;
        int end = target > nums[last] ? minIndex : last;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) return mid;
            if (target > nums[mid]) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }


    public int findMinIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray testClass = new SearchInRotatedSortedArray();
        int[] nums = {4,5,6,1,2,3};
        int target = 2;
        int targetIndex = testClass.findTarget(nums, target);
        System.out.println(targetIndex);
    }

}
