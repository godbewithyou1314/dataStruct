/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.String;

/**
 * Description:
 * 实现一个正则表达式（.和*）
 * Regular Expression Matching
 * <p>
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * <p>
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 * <p>
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * <p>
 * 1. 正常字母：依次比对
 * 2. 出现*：
 *
 * @author zhangwei111
 * Created on 2019-12-17 15:52
 */
public class RegularExpressionMatching {

    public static boolean isMatchByDP(String s, String p) {
        boolean[][] result = new boolean[s.length() + 1][p.length() + 1];
        result[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*' && result[0][j]) {
                result[0][j + 1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                result[i + 1][j + 1] = isMatch(s, p, i, j, result);
            }
        }
        return result[s.length()][p.length()];
    }

    private static boolean isMatch(String s, String p, int sPoint, int pPoint, boolean[][] result) {
        switch (p.charAt(pPoint)) {
            case '.':
                return result[sPoint][pPoint];
            case '*':
                // abc -- abcd*
                if (p.charAt(pPoint - 1) != s.charAt(sPoint) && p.charAt(pPoint - 1) != '.') {
                    return result[sPoint + 1][pPoint - 1];
                }
                // (aaa,a*)                            (a, a*)                            没匹配
                return (result[sPoint][pPoint + 1] || result[sPoint + 1][pPoint] || result[sPoint + 1][pPoint - 1]);
            default:
                // a = b, 纯字符比对
                if (s.charAt(sPoint) == p.charAt(pPoint)) {
                    return result[sPoint][pPoint];
                } else {
                    return false;
                }
        }
    }

    public static boolean isMatchs(String s, String p) {
        int sPoint = 0;
        int pPoint = 0;
        while (sPoint < s.length() && pPoint < p.length()) {
            char curPChar = p.charAt(pPoint);
            char curSChar = s.charAt(sPoint);
            if ((pPoint + 1) < p.length() && p.charAt(pPoint + 1) == '*') {
                // a*
                if (curPChar != '.') {
                    while (sPoint < s.length() && s.charAt(sPoint) == curPChar) {
                        sPoint++;
                    }
                    pPoint = pPoint + 2;
                } else {
                    // .*
                    if (pPoint + 2 < p.length()) {
                        while (p.charAt(pPoint + 2) != s.charAt(sPoint)) {
                            if (sPoint == s.length() - 1) {
                                return false;
                            }
                            sPoint++;
                        }
                        pPoint = pPoint + 2;
                    } else {
                        return true;
                    }
                }
            } else if (curPChar == '.' || s.charAt(sPoint) == p.charAt(pPoint)) {
                sPoint++;
                pPoint++;
            } else {
                return false;
            }
        }

        if (sPoint == s.length() && pPoint == p.length()) {
            return true;
        }
        if (sPoint == s.length() && p.charAt(pPoint) == '*' && p.charAt(pPoint + 1) == s.charAt(sPoint - 1)) {
            return true;
        }
        return false;
    }

    public static boolean isMatchO(String s, String p) {
        int sPoint = 0;
        int pPoint = 0;
        while (sPoint < s.length() && pPoint < p.length()) {
            switch (p.charAt(pPoint)) {
                case '.':
                    sPoint++;
                    pPoint++;
                    break;
                case '*':
                    if (p.charAt(pPoint - 1) == '.') {
                        if (pPoint == p.length() - 1) {
                            return true;
                        }
                        while (sPoint < s.length() && p.charAt(pPoint + 1) != s.charAt(sPoint)) {
                            sPoint++;
                        }
                        if (sPoint == s.length()) {
                            return false;
                        }
                    } else {
                        while (sPoint < s.length() && p.charAt(pPoint - 1) == s.charAt(sPoint)) {
                            sPoint++;
                        }
                    }
                    pPoint++;
                    break;
                default:
                    if (s.charAt(sPoint) != p.charAt(pPoint)) {
                        if (pPoint + 1 < p.length() && p.charAt(pPoint + 1) == '*') {
                            pPoint = pPoint + 2;
                        } else {
                            return false;
                        }
                    } else {
                        sPoint++;
                        pPoint++;
                    }
            }
        }
        if (sPoint == s.length() && pPoint == p.length()) {
            return true;
        }
        if (sPoint == s.length()
                && p.charAt(pPoint - 1) == '*'
                && p.charAt(pPoint) == s.charAt(sPoint - 1)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatchByDP(s, p));
    }
}
