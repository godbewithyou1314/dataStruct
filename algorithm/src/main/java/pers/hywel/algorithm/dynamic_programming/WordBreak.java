package pers.hywel.algorithm.dynamic_programming;

import java.util.Arrays;
import java.util.List;

/**
 * 139. Word Break【Medium】
 * <p>
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 *
 * @Date 2022-12-29
 */
public class WordBreak {
    // dp[i + 1] =  (dp[i - word1.length] && isMatch[start1, word1])
    //              || (dp[i - word2.length] && isMatch[start2, word2])
    //              || (dp[i - word3.length] && isMatch[start3, word3])
    //              || ...
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] dp = new Boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            boolean cur = false;
            for (String word : wordDict) {
                if (i + 1 >= word.length()
                        && dp[i + 1 - word.length()]
                        && containWordFromStart(s, i + 1 - word.length(), word)) {
                    cur = true;
                    break;
                }
            }
            dp[i + 1] = cur;
        }
        return dp[s.length()];
    }

    private boolean containWordFromStart(String s, int start, String word) {
        if (s.length() - start < word.length()) return false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != s.charAt(start + i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "leetcodea";
        String[] wordDict = new String[]{"leet", "code"};
        List<String> wordDictList = Arrays.asList(wordDict);

        WordBreak testObj = new WordBreak();
        System.out.println(testObj.wordBreak(s, wordDictList));

    }
}
