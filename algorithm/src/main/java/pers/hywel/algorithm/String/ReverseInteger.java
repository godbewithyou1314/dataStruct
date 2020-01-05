/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.String;

/**
 * Description:
 *  数字翻转
 * Solution
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 * Input: 123
 * Output: 321
 *
 * @author zhangwei111
 * Created on 2019-12-04 22:23
 */
public class ReverseInteger {
    public static int reverse(int x) {
        int result = 0;
        int newResult;
        while (x != 0) {
            newResult = result * 10 + (x %10);
            // 溢出判断
            if (newResult / 10 != result) {
                return 0;
            }
            result = newResult;
            x /= 10;
        }
        return result;
    }

    /**
     * 解决方法01
     * 17ms， 效率不行。主要记录 1<<31 是最大负数的操作
     * @param x
     * @return
     */
    public static int reverse01(int x) {
        long result = 0;
        while (x != 0) {
            result *= 10;
            result += (x % 10);
            System.out.println(result);
            // 1<<31 最大的负数， -1 最大的正数
            if (result > ((1 << 31) - 1) || result < (1 << 31)) {
                return 0;
            }
            x /= 10;
        }
        return (int)result;
    }

    public static void main(String[] args) {
        int test = -2147483648;
        int result = reverse(test);
        System.out.println(result);
    }
}
