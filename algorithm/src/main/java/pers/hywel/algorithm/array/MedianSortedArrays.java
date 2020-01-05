/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.array;

/**
 * Description:
 * 找两个以排序的非空数组的中位数
 * 【Hard】Median of Two Sorted Arrays
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 *
 * Example 2:
 *
 * nums1 = [1, 2, 3]
 * nums2 = [4, 5, 6]
 *
 * The median is (2 + 3)/2 = 2.5
 *
 * @author RobertZhang
 * Created on 2019-12-12 14:25
 */
public class MedianSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int count = (int) Math.ceil(((double) nums1.length + (double) nums2.length)/2);
        int nums1StartPoint = 0;
        int nums1EndPoint = nums1.length-1;
        int nums2StartPoint = 0;
        int nums2EndPoint = nums2.length-1;

        int medianMin = 0,medianMax = 0;
        while (count > 0) {
            if (nums1StartPoint > nums1.length - 1 || nums1EndPoint < 0) {
                return getMedianFromSingleArray(nums2, nums2StartPoint, nums2EndPoint);
            }
            if (nums2StartPoint > nums2.length - 1 || nums2EndPoint < 0) {
                return getMedianFromSingleArray(nums1, nums1StartPoint, nums1EndPoint);
            }
            medianMin = nums1[nums1StartPoint] < nums2[nums2StartPoint] ?
                    nums1[nums1StartPoint++] : nums2[nums2StartPoint++];
            medianMax = nums1[nums1EndPoint] > nums2[nums2EndPoint] ?
                    nums1[nums1EndPoint--] : nums2[nums2EndPoint--];
            count--;
        }
        return ((double) medianMin + (double) medianMax) / 2;
    }

    // 获取单个数组的中位数
    private static double getMedianFromSingleArray(int[] singleNums, int start, int end) {
        int length = end - start + 1;
        if (length % 2 == 0)
            return ((double) singleNums[(start + end)/2] + (double) singleNums[(start + end)/2 + 1]) / 2;
        else
            return (double) singleNums[(start + end)/2];
    }

    public static void main(String[] args) {
        int[] a = new int[]{4};
        int[] b = new int[]{1, 2, 3};
        System.out.println(findMedianSortedArrays(a, b));
    }
}
