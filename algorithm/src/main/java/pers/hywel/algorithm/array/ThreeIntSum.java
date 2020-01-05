/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author zhangwei111
 * Created on 2020-01-02 15:02
 */
public class ThreeIntSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        Set<String> existList = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i=0; i< nums.length; i++) {
            numsMap.put(nums[i], i);
        }
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                int key = 0 - nums[i] - nums[j];
                if (numsMap.containsKey(key)
                        && numsMap.get(key) != i
                        && numsMap.get(key) != j) {
                    int[] array = {nums[i],nums[j], key};
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
