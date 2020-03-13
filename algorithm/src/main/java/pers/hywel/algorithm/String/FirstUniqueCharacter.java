/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.String;

import java.util.HashMap;

/**
 * Description:
 * 查找字符串中第一个不相同的字符
 * Solution
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 *
 * @author RobertZhang
 *

 */
public class FirstUniqueCharacter {
    /**
     * 方法一(假定所有都是小写字母)
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        if (null == s) {
            return -1;
        }
        int[] count = new int[26];
        for (int i=0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i=0; i < s.length();i++) {
            if (count[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    /**
     * 方法二，通过map解决。时间：O(n+n)
     *
     * @param s
     * @return
     */
    public static int firstUniqCharByMap(String s) {
        if (null == s) {
            return -1;
        }
        HashMap<Character, Integer> map = new HashMap<>(s.length());
        for (int i=0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i=0; i < s.length();i++) {
            if (map.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;

    }

    public static void main(String[] args) {
        String test = "eetcode";
        System.out.println(firstUniqChar(test));
    }
}
