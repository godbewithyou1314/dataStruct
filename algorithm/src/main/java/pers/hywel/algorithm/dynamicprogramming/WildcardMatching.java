/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.dynamicprogramming;

/**
 * Description:
 *
 * 44. Wildcard Matching
 * Hard
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * Example 5:
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 *
 * @author RObertZhang
 * Created on 2020-05-14
 */
public class WildcardMatching {

    /**
     * 非DP
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sIndex = 0, pIndex = 0, asteriskMatched = 0 , asteriskPoint = -1;
        while (sIndex < s.length()) {
            if (pIndex < p.length() && (p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '?')) {
                sIndex++;
                pIndex++;
            }
            else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
                asteriskPoint = pIndex;
                asteriskMatched = sIndex;
                pIndex++;
            } else if (asteriskPoint != -1) {
                pIndex = asteriskPoint + 1;
                asteriskMatched++;
                sIndex = asteriskMatched;
            }
            else return false;
        }

        while (pIndex < p.length() && p.charAt(pIndex) == '*')
            pIndex++;
        return pIndex == p.length();

    }

    /**
     * 二维DP，dp解雷同题目：RegularExpressionMatching
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatchByDP(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j];
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j =  0; j < p.length(); j++) {
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '?') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    // '*'不匹配任何字符
                    if (dp[i + 1][j]) dp[i + 1][j + 1] = true;
                        // '*'匹配一个或多个随机字符
                    else {
                        for (int k = i; k >= 0; k--) {
                            if (dp[k][j]) {
                                dp[i + 1][j + 1] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    public static void main(String[] args) {
        String s = "a*aa";
        String p = "**a*";
        System.out.println(isMatch(s, p));
    }
}
