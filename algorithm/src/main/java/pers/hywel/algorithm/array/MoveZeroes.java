/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.array;

import java.util.Arrays;

/**
 * Description:
 *  Move Zeroes
 *  把0都移动到数组末尾（维持非0元素顺序）
 *
 * @author RobertZhang
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int gap = 1;
        for (int i=0; i < nums.length-1; i++) {
            if (nums[i] == 0) {
                while (i+gap < nums.length && nums[i+gap] == 0) {
                    gap++;
                }
                if (i+gap >= nums.length) {
                    return;
                }
                nums[i] = nums[i+gap];
                nums[i+gap] = 0;
            }
        }
    }


    /**
     * main方法
     *
     * @param args
     */
    public static void main(String[] args) {
        MoveZeroes moveZeroesClass = new MoveZeroes();
        int[] test = {1, 0, 2, 0, 3, 0};
        moveZeroesClass.moveZeroes(test);
        System.out.print(Arrays.toString(test));
    }
}
