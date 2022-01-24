package pers.hywel.algorithm.backtracking;

import pers.hywel.algorithm.String.ValidPalindrome;
import pers.hywel.algorithm.common.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning【Medium】
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * <p>
 * A palindrome string is a string that reads the same backward as forward.
 * <p>
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * <p>
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 *
 * @Date 2021-12-31
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backTracking(s, new ArrayList<>(), result);
        return result;
    }

    private void backTracking(String s, List<String> tempList, List<List<String>> result) {
        if (s.length() == 0) {
            result.add(new ArrayList<>(tempList));
        }
        for (int i = 1; i <= s.length(); i++) {
            String leftStr = s.substring(0, i);
            if (ValidPalindrome.isPalindrome(leftStr)) {
                tempList.add(leftStr);
                backTracking(s.substring(i), tempList, result);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartitioning testObj = new PalindromePartitioning();
        String s = "a";
        List<List<String>> result = testObj.partition(s);
        PrintUtils.printList(result);
    }
}
