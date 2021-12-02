
package pers.hywel.algorithm.binarysearch;


/**
 * 81. Search in Rotated Sorted Array II
 * Medium
 * <p>
 * There is an integer array nums sorted in non-decreasing order
 * (not necessarily with distinct values).
 * <p>
 * Before being passed to your function,
 * nums is rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 * <p>
 * Given the array nums after the rotation and an integer target,
 * return true if target is in nums, or false if it is not in nums.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums is guaranteed to be rotated at some pivot.
 * -104 <= target <= 104
 * <p>
 * <p>
 * Follow up: This problem is similar to Search in Rotated Sorted Array,
 * but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 */
public class SearchInRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;
            // 1. 如果左部分有序
            if (nums[start] < nums[mid]) {
                if (nums[mid] < target || target < nums[start]) start = mid + 1;
                else end = mid - 1;
            } else if (nums[start] > nums[mid]) {
                // 2. 右边部分有序
                if (nums[mid] > target || target > nums[end]) end = mid - 1;
                else start = mid + 1;
            } else {
                // 3. 如果左右相等，边界减1，重新检测
                start++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray2 test = new SearchInRotatedSortedArray2();
        int[] nums = new int[]{2,2,2,2,3,2};
        System.out.println(test.search(nums, 3));
    }
}
