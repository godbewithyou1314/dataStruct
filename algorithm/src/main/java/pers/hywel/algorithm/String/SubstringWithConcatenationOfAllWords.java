
package pers.hywel.algorithm.String;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * 30. Substring with Concatenation of All Words
 * Hard
 *
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices
 * of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening
 * characters.
 * 所有words都是一样长度
 *
 * Example 1:
 * Input:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 *
 * Input:
 *   s = "wordgoodwordbestbestword",
 *   words = ["word","good","best","word"]
 * Output: []
 *
 * @author zhangwei111
 * Created on 2020-04-24 16:18
 */
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (words == null || words.length < 1 || s.isEmpty()) return result;
        HashMap<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        int wordLength = words[0].length();
        for (int i = 0; i < s.length() - words.length * wordLength + 1; i++) {
            HashMap<String, Integer> seen = new HashMap<>();
            int wordIterator = 0;
            while (wordIterator < words.length) {
                int startIndex = i + wordIterator * wordLength;
                String subString = s.substring(startIndex, startIndex + wordLength);
                if (wordsMap.containsKey(subString)) {
                    seen.put(subString, seen.getOrDefault(subString, 0) + 1);
                    if (seen.get(subString) > wordsMap.get(subString)) break;
                    wordIterator++;
                } else break;
            }
            if (wordIterator == words.length) result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords testClass = new SubstringWithConcatenationOfAllWords();
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        List<Integer> result = testClass.findSubstring(s, words);
        for (Integer index : result) {
            System.out.println(index);
        }
    }
}
