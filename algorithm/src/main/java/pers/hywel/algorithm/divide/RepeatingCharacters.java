package pers.hywel.algorithm.divide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 395. Longest Substring with At Least K Repeating Characters【Medium】
 *
 * Given a string s and an integer k, return the length of the longest substring of s such that
 * the frequency of each character in this substring is greater than or equal to k.
 *
 * Example 1:
 * Input: s = "aadabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 *
 * Example 2:
 * Input: s = "ababbc", k = 2
 * Output: 5
 * Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of only lowercase English letters.
 * 1 <= k <= 105
 */
public class RepeatingCharacters {

    /**
     * 标准解答： 双指针
     * 时间复杂度O(N)
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] counts = new int[26];
        int h, i, j, idx, max = 0, unique, noLessThanK;

        for (h = 1; h <= 26; h++) {
            Arrays.fill(counts, 0);
            i = 0;
            j = 0;
            unique = 0;
            noLessThanK = 0;
            while (j < str.length) {
                if (unique <= h) {
                    idx = str[j] - 'a';
                    if (counts[idx] == 0)
                        unique++;
                    counts[idx]++;
                    if (counts[idx] == k)
                        noLessThanK++;
                    j++;
                }
                else {
                    idx = str[i] - 'a';
                    if (counts[idx] == k)
                        noLessThanK--;
                    counts[idx]--;
                    if (counts[idx] == 0)
                        unique--;
                    i++;
                }
                if (unique == h && unique == noLessThanK)
                    max = Math.max(j - i, max);
            }
        }

        return max;
    }


    /**
     * 自己解法： 分治，从小于k次数的字符处分割成子串进行处理。如果子串的每个字符出现次数都大于k，那么子串长度就是最大长度
     * 时间复杂度 O(nlogn)
     * @param s
     * @param k
     * @return
     */
    public int longestSubstringMyself(String s, int k) {
        if (k == 1) return s.length();
        Map<Character, Integer> charFreMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character curChar = s.charAt(i);
            charFreMap.put(curChar, charFreMap.getOrDefault(curChar, 0) + 1);
        }
        if (isAllGoeK(charFreMap, k)) {
            return s.length();
        } else {
            List<String> subStrs = splitByMulChar(s, charFreMap, k);
            int maxSize = 0;
            for (String subStr : subStrs) {
                if (subStr.length() >= k) {
                    maxSize = Math.max(maxSize, longestSubstring(subStr, k));
                }
            }
            return maxSize;
        }
    }

    private List<String> splitByMulChar(String s, Map<Character, Integer> charFreMap, int k) {
        List<String> subStrs = new ArrayList<>();
        int lastIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (charFreMap.get(s.charAt(i)) < k) {
                if (i - lastIndex >= k) subStrs.add(s.substring(lastIndex, i));
                lastIndex = i + 1;
            }
        }
        if (s.length() - lastIndex >= k) subStrs.add(s.substring(lastIndex));
        return subStrs;
    }

    private boolean isAllGoeK(Map<Character, Integer> charFreMap, int k) {
        for (Integer frequency : charFreMap.values()) {
            if (frequency < k) return false;
        }
        return true;
    }




    public static void main(String[] args) {
        RepeatingCharacters testObj = new RepeatingCharacters();
        String str = "bbaaacbd";
        int k = 3;
        System.out.println(testObj.longestSubstring(str, k));
    }
}
