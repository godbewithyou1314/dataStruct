package pers.hywel.algorithm.windows;

/**
 * 76. Minimum Window Substring【Hard】
 * <p>
 * Given two strings s and t of lengths m and n respectively,
 * return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 * <p>
 * <p>
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 *
 * @Date 2022-03-16
 */
public class MinimumWindowSubstring {
    /**
     * 参考Permutation实现，使用一个window窗口，记录当前s和t的滑动窗口中，是否包含所有t的全部字母
     * 然后滑动window，每次加入window末尾元素再判断，移除window头元素。
     * 当发现是满足条件的子串时，缩小window，再次看是否满足条件。一直缩小到不满足为止，再往后查找
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        String minResult = "";
        int[] window = new int[57];
        boolean isContainAllChar = false;
        for (int i = 0; i < t.length(); i++) {
            window[t.charAt(i) - 'A']++;
            window[s.charAt(i) - 'A']--;
        }

        if (containAllChar(window)) {
            minResult = s.substring(0, t.length());
        }

        int start = 0, end = t.length();
        while (end < s.length()) {
            window[s.charAt(end) - 'A']--;
            isContainAllChar = containAllChar(window);
            while (isContainAllChar) {
                if ("".equals(minResult) || (end - start + 1) < minResult.length()) {
                    minResult = s.substring(start, end + 1);
                }
                window[s.charAt(start) - 'A']++;
                isContainAllChar = containAllChar(window);
                start++;
            }
            end++;
        }
        return minResult;
    }

    private boolean containAllChar(int[] window) {
        for (int num : window) {
            if (num > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring testObj = new MinimumWindowSubstring();
        String s = "abcdfeg";
        String t = "fge";
        String min = testObj.minWindow(s, t);
        System.out.println(min);
    }
}
