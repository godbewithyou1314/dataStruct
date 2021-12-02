
package pers.hywel.algorithm.topinterview;

/**
 * Description:
 *   Merge Sorted Array
 *
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements
 * from nums2.
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 *
 * @author zhangwei111
 * Created on 2020-05-30 18:43
 */
public class MergeSortedArray {
    /**
     * 从最大元素开始插入，时间复杂度O(m+n)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int point1 = m - 1, point2 = n - 1;
        for (int i = 1; i <= m + n; i++) {
            if (point2 < 0) {
                return;
            } else if (point1 < 0) {
                nums1[m + n - i] = nums2[point2];
                point2--;
            } else if (nums1[point1] > nums2[point2]) {
                nums1[m + n - i] = nums1[point1];
                point1 --;
            } else {
                nums1[m + n - i] = nums2[point2];
                point2 --;
            }
        }
    }
}
