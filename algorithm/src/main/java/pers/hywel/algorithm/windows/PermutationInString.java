package pers.hywel.algorithm.windows;

/**
 * 567. Permutation in String【Medium】
 *
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * Example 1：
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 * Constraints:
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.
 *
 * @Date 2022-02-11
 */
public class PermutationInString {
    /*
        滑动窗口
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] windows = new int[26];
        // 初始化一个s1长度的窗口
        for (int i = 0; i < s1.length(); i++) {
            windows[s1.charAt(i) - 'a']++;
            windows[s2.charAt(i) - 'a']--;
        }
        // 如果窗口内，所有元素为0.证明正好包含所有字母
        if (allZero(windows)) return true;
        // 每次往窗口前进一个字符，移出一个字符
        for (int i = s1.length(); i < s2.length(); i++) {
            windows[s2.charAt(i) - 'a']--;
            windows[s2.charAt(i - s1.length()) - 'a']++;
            if (allZero(windows)) return true;
        }
        return false;
    }

    private boolean allZero(int[] nums) {
        for (int num : nums) {
            if (num != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PermutationInString testObj = new PermutationInString();
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(testObj.checkInclusion(s1, s2));
    }
}
