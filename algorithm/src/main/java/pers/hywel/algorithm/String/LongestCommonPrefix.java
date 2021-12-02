
package pers.hywel.algorithm.String;

import java.util.ArrayList;

/**
 * Description:
 * Longest Common Prefix
 * 寻找字符串含有的最长相同前缀
 * <p>
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * <p>
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 * <p>
 * All given inputs are in lowercase letters a-z.
 *
 * @author RobertZhang
 *

 */
public class LongestCommonPrefix {
    /**
     * 方法二 水平扫描
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }
    /**
     * 方法一 竖直扫描
     * @param strs
     * @return
     */
    public String longestCommonPrefixByMyself(String[] strs) {
        if (null == strs || strs.length < 1)
            return "";
        if (strs.length == 1)
            return strs[0];
        StringBuilder commonPrefix = new StringBuilder();
        int currentIndex = 0;
        while (true) {
            for (int i = 0; i < strs.length - 1; i++) {
                if (currentIndex >= strs[i].length() || currentIndex >= strs[i + 1].length()
                        || strs[i].charAt(currentIndex) != strs[i + 1].charAt(currentIndex)) {
                    return commonPrefix.toString();
                }
            }
            commonPrefix.append(strs[0].charAt(currentIndex++));
        }
    }

    public static void main(String[] args) {
        LongestCommonPrefix testClass = new LongestCommonPrefix();
        String[] test = new String[]{""};
        System.out.println(testClass.longestCommonPrefix(test));
    }
}
