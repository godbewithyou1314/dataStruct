/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.String;

/**
 * Description:
 * 验证回文
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 *
 * @author zhangwei111
 * Created on 2019-12-06 20:09
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (null == s || s.isEmpty()) {
            return true;
        }
        int i=0;
        int j=s.length()-1;
        while (i<j) {
            if (!Character.isDigit(s.charAt(i)) && !Character.isLetter(s.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isDigit(s.charAt(j)) && !Character.isLetter(s.charAt(j))) {
                j--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String test = "0P";
        System.out.println(isPalindrome(test));
    }
}
