/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.dynamicprogramming;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * 46. Permutations
 * Medium
 *
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * @author RobertZhang
 * Created on 2020-05-20
 */
public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 1) return res;
        List<Integer> firstList = new LinkedList<>();
        firstList.add(nums[0]);
        res.add(firstList);
        for (int i = 1; i < nums.length; i++) {
            List<List<Integer>> subRes = new LinkedList<>();
            for (List subList : res) {
                for (int k = 0; k <= subList.size(); k++) {
                    List<Integer> list = new LinkedList<>(subList);
                    list.add(k, nums[i]);
                    subRes.add(list);
                }
            }
            res.addAll(subRes);
            int currentLength = i + 1;
            res = res.stream().filter(list -> list.size() == currentLength).collect(Collectors.toList());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3};
        List<List<Integer>> res = permute(array);
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}
