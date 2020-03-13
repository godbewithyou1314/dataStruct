/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.array;

import java.util.Arrays;

/**
 * Description:
 *    Plus One
 *      给定一个数字数组，进行加1
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 *
 * @author RobertZhang
 */
public class PlusOne {
    private int[] plusOne(int[] digits) {
        int flag;
        for (int i=digits.length-1; i>=0;) {
            flag = digits[i] + 1 == 10 ? 1:0;
            digits[i] = (digits[i] + 1) % 10;
            if (flag == 1) {
                if (i == 0) {
                    int[] newArray = new int[digits.length+1];
                    newArray[0] = 1;
                    System.arraycopy(digits, 0, newArray, 1, digits.length);
                    return newArray;
                }
                i--;
            } else {
                return digits;
            }
        }
        return digits;
    }

    /**
     * main方法
     *
     * @param args
     */
    public static void main(String[] args) {
            PlusOne plusOneArray = new PlusOne();
            int[] test = {9, 9, 9};
            int[] a = plusOneArray.plusOne(test);
            System.out.print(Arrays.toString(a));
    }
}
