/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.SoundbankResource;

/**
 * Description:
 * Combination Sum II
 * Medium
 *
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
 * candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * @author RobertZhang
 * Created on 2020-05-31
 */
public class CombinationSum2 {
    /**
     * diff : combination sum --> 不能重复使用元素
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                // 如果当前数等于前面一个数，该节点下的所有不用再次遍历
                if (i > start && nums[i] == nums[i - 1]) continue;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i + 1); // i + 1 because we can not reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    /**
     * 数组去重
     * @param nums
     * @return
     */
    private int[] distinct(int[] nums) {
        int gap = 0;
        for (int i = 0; i + gap + 1 < nums.length;) {
           if (nums[i] == nums[i + gap + 1]) {
               gap++;
           } else {
               i++;
               swap(nums, i, i + gap);
           }
        }
        return Arrays.copyOf(nums, nums.length - gap);
    }

    private void swap(int[] nums, int a, int b) {
        int swap = nums[a];
        nums[a] = nums[b];
        nums[b] = swap;
    }

    public static void main(String[] args) {
        CombinationSum2 obj = new CombinationSum2();
        int[] a = new int[]{1,1,2,2,3,4,5};
        for (int i : obj.distinct(a)) {
            System.out.println(i);
        }
    }

}
