package pers.hywel.algorithm.doublepoint;

import java.util.HashMap;
import java.util.Map;


/**
 * 3. Longest Substring Without Repeating Characters 【Medium】
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.isEmpty()) { return 0; }
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxNoRepeatSubStrLen = 0;
        for (int head=0,tail=0; tail < s.length(); tail++) {
            if(charIndexMap.containsKey(s.charAt(tail))){
                //头指针不能回头
                head = Math.max(head,charIndexMap.get(s.charAt(tail))+1);
            }
            maxNoRepeatSubStrLen = Math.max(maxNoRepeatSubStrLen,tail-head+1);
            charIndexMap.put(s.charAt(tail), tail);
        }
        return maxNoRepeatSubStrLen;
    }

    /**
     * dp解法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringWithDP(String s) {
        int[] dp = new int[s.length()];
        Map<Character, Integer> occur = new HashMap<>();

        dp[0] = 1;
        occur.put(s.charAt(0), 0);
        for (int i = 1; i < s.length(); i++) {
            Character curChar = s.charAt(i);
            dp[i] = occur.containsKey(curChar) && i - occur.get(curChar) <= dp[i - 1] ? i - occur.get(curChar) : dp[i - 1] + 1;
            occur.put(curChar, i);
        }
        int max = 0;
        for (int i : dp) {
            max = Math.max(i, max);
        }
        return max;
    }

    /**
     * 双指针解法
     * @param s
     * @return
     */
    public String lengthOfLongestSubstringMyself(String s) {
        int start = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        String maxUnRepeatStr = "";
        for (int i = 0; i < s.length(); i++) {
            Character curChar = s.charAt(i);
            if (charIndexMap.containsKey(curChar) && charIndexMap.get(curChar) >= start) {
                if (i - start > maxUnRepeatStr.length()) {
                    maxUnRepeatStr = s.substring(start, i);
                }
                start = charIndexMap.get(curChar) + 1;
            }
            charIndexMap.put(curChar, i);
        }
        maxUnRepeatStr = s.length() - start > maxUnRepeatStr.length() ? s.substring(start) : maxUnRepeatStr;
        return maxUnRepeatStr;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters test = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(test.lengthOfLongestSubstring("aab"));
    }

}
