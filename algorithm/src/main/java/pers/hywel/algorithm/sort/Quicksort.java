/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.sort;

import java.util.Arrays;

/**
 * Description:
 * 快排
 * <p>
 * 思想：分治
 * 步骤：
 * 1. 挑选基准
 * 2. 所有基准小的放前边，基准大的放后边(原地排序，使用指针记录)
 * 3. 递归排序子序列
 *
 * @author RobertZhang
 */
public class Quicksort {

    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    /**
     * 闭区间
     *
     * @param nums
     * @param left
     * @param right
     */
    public void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            quickSort(nums, left, partitionIndex - 1);
            quickSort(nums, partitionIndex + 1, right);
        }
    }

    /**
     * 分区
     *
     * @param a
     * @param left
     * @param right （包含right, 闭区间）
     *
     * @return
     */
    private int partition(int[] a, int left, int right) {
        // 最后一个数字作为比较基准
        int pivotValue = a[right];
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (a[i] <= pivotValue) {
                swap(a, i, storeIndex);
                storeIndex++;
            }
        }
        swap(a, storeIndex, right);
        return storeIndex;
    }

    // 交换a、b下标的数组值
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 5, 3, 2};
        Quicksort quicksort = new Quicksort();
        quicksort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
