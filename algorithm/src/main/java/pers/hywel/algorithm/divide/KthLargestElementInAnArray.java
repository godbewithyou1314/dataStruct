package pers.hywel.algorithm.divide;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array 【Medium】
 * 数组的top k问题
 * <p>
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * <p>
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * Constraints:
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 *
 * @Date 2022-02-28
 */
public class KthLargestElementInAnArray {

    /**
     * 解法一：quick select 快排变种。每次丢掉一部分。分治思想
     * O(N) running time + O(1) memory
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    int quickSelect(int[] nums, int low, int high, int k) {
        int pivot = low;

        // use quick sort's idea
        // put nums that are <= pivot to the left
        // put nums that are  > pivot to the right
        for (int j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                //  ++ in method, unreadable, bad write!!!
                swap(nums, pivot++, j);
            }
        }
        swap(nums, pivot, high);

        // count the nums that are > pivot from high
        int count = high - pivot + 1;
        // pivot is the one!
        if (count == k) return nums[pivot];
        // pivot is too small, so it must be on the right
        if (count > k) return quickSelect(nums, pivot + 1, high, k);
        // pivot is too big, so it must be on the left
        return quickSelect(nums, low, pivot - 1, k - count);
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 解法二：
     * 优先队列。小顶堆
     * O(N) best case / O(N^2) worst case running time + O(1) memory
     */
    public int findKthLargestByQueue(int[] nums, int k) {

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.offer(val);

            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * 解法三：
     * 排序加 top k
     * O(N lg N) running time + O(1) memory
     */
    public int findKthLargestBySort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        KthLargestElementInAnArray testObj = new KthLargestElementInAnArray();
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(testObj.findKthLargest(nums, k));
    }
}
