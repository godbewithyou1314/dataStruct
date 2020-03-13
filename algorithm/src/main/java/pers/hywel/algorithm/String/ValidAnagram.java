/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.String;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * 判断同字母异序词
 *
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 * @author RobertZhang
 *

 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> charNums = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            charNums.put(s.charAt(i), charNums.getOrDefault(s.charAt(i), 0)+1);
        }
        for (int i=0; i<t.length(); i++) {
            Integer num = charNums.get(t.charAt(i));
            if(null != num) {
                if (num == 1) {
                    charNums.remove(t.charAt(i));
                } else {
                    charNums.put(t.charAt(i), num - 1);
                }
            } else {
                return false;
            }
        }
        return charNums.isEmpty();
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }
}
