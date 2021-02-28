/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 47. Permutations II [Medium]
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * Solve:
 *  backtracking
 *
 * @author RobertZhang
 * Created on 2020-06-15
 */
public class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<String, List<Integer>> resMap = new HashMap<>();
        backTracking(resMap, new LinkedList<>(), nums, 0);
        return new ArrayList<>(resMap.values());
    }

    private void backTracking(Map<String, List<Integer>> resMap, List<Integer> tempRes, int[] nums, int start) {
        if (start == nums.length) {
            StringBuilder key = new StringBuilder();
            List<Integer> oneRes = new ArrayList<>();
            for (Integer integer : tempRes) {
                key.append(integer);
                oneRes.add(integer);
            }
            resMap.put(key.toString(), oneRes);
        }
        else if (start < nums.length) {
            for (int i = start; i < nums.length; i++) {
                swap(nums, start, i);
                tempRes.add(nums[start]);

                backTracking(resMap, tempRes, nums, start + 1);

                swap(nums, i, start);
                tempRes.remove(tempRes.size() - 1);
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        Permutations2 permutations = new Permutations2();
        int[] array = new int[]{1, 1, 3};
        List<List<Integer>> res = permutations.permuteUnique(array);
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}
