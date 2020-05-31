/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.dynamicprogramming;

/**
 * Description:
 * 实现一个正则表达式（.和*）
 * 10.Regular Expression Matching
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
 * @author RobertZhang
 *

 */
public class RegularExpressionMatching {
    /**
     1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
     2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
     3, If p.charAt(j) == '*':
     here are two sub conditions:
        1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
        2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
        dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
        or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
        or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     */
    public static boolean isMatchByDP(String s, String p) {
        if (s == null || p == null) return false;
        // dp[i+1][j+1] 存储i与j的匹配结果
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*' && dp[0][j - 1])
                dp[0][j + 1] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    // p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] : a*为空匹配，直接去掉
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j-1) != '.') dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    // p.charAt(i-1) == s.charAt(i) ==>
                        // 1. a* 空匹配 dp[i][j] = dp[i][j-2]
                        // 2. a* 匹配单个a dp[i][j] = dp[i][j-1]
                        // 3. a* 匹配多个a dp[i][j] = dp[i-1][j]
                    else dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || dp [i][j + 1];
                }
            }
        }
        return dp[s.length()][p.length()];

    }


    public static void main(String[] args) {
        String s = "";
        String p = "c*a*";
        System.out.println(isMatchByDP(s, p));
    }
}
