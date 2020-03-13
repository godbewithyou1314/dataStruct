/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.String;

/**
 * Description:
 * 字符串翻转
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1)
 * extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 *
 * @author RobertZhang
 *

 */
public class ReverseString {
    public static void reverseString(char[] s) {
        if (null == s || s.length < 2)
            return;
        for (int i=0; i < s.length/2; i++) {
            char swap = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = swap;
        }
    }

    public static void main(String[] args) {
        char[] test = {'h','e','l','l','o'};
        reverseString(test);
        System.out.println(test);
    }
}
