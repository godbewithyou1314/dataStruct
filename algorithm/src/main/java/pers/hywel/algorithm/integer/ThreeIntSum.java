/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.integer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * 3sum  三数相加等于0
 * <p>
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique
 * triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * @author RobertZhang
 *

 */
public class ThreeIntSum {

    /**
     * 先排序
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                // 因为已排序，最右边数一定大于等于0，最左边数一定小于等于0，相加才能为0
                while (lo < hi && nums[i] <= 0 && nums[hi] >= 0) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    /**
     * O(n^3)
     *
     * @param nums
     *
     * @return
     */
    public List<List<Integer>> threeSumByMyself(int[] nums) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        Set<String> existList = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int key = 0 - nums[i] - nums[j];
                if (numsMap.containsKey(key)
                        && numsMap.get(key) != i
                        && numsMap.get(key) != j) {
                    int[] array = {nums[i], nums[j], key};
                    Arrays.sort(array);
                    String setKey = "" + array[0] + array[1] + array[2];
                    if (!existList.contains(setKey)) {
                        result.add(Arrays.asList(nums[i], nums[j], key));
                        existList.add(setKey);
                    }

                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeIntSum testClass = new ThreeIntSum();
        int[] testArray = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = testClass.threeSum(testArray);
        result.forEach(list -> System.out.println(list.toString()));
    }
}
