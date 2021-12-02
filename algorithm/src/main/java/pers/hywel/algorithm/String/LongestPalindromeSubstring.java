
package pers.hywel.algorithm.String;

/**
 * Description:
 * 最长回文子串
 * 5. <Medium>Longest Palindromic Substring
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * @author RobertZhang
 *

 */
public class LongestPalindromeSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    // solves02 by myself
    public String longestPalindrome02(String s) {
        if (s.length()<2) {
            return s;
        }
        String maxPalindromeSubstring = String.valueOf(s.charAt(0));
        for (int i=0; i<s.length(); i++) {
            for (int j=0; j<i-maxPalindromeSubstring.length()+1;) {
                if (isPalindrome02(s, j, i)) {
                    maxPalindromeSubstring = s.substring(j, i+1);
                    break;
                }
                else
                    j++;
            }
        }
        return maxPalindromeSubstring;
    }
    // 判读是否为回文
    private boolean isPalindrome02(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;

    }

    public static void main(String[] args) {
        LongestPalindromeSubstring longestPalindromeSubstring = new LongestPalindromeSubstring();
        String test = "bb";
        System.out.println(longestPalindromeSubstring.longestPalindrome02(test));
    }
}
