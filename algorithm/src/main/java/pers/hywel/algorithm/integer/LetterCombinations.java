package pers.hywel.algorithm.integer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Letter Combinations of a Phone Number
 *
 Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

 A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

 Example:

 Input: "23"
 Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author RobertZhang
 *

 */
public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        Map<Character, List<String>> intStringMap = new HashMap<>();
        intStringMap.put('2', Arrays.asList("a", "b", "c"));
        intStringMap.put('3', Arrays.asList("d", "e", "f"));
        intStringMap.put('4', Arrays.asList("g", "h", "i"));
        intStringMap.put('5', Arrays.asList("j", "k", "l"));
        intStringMap.put('6', Arrays.asList("m", "n", "o"));
        intStringMap.put('7', Arrays.asList("p", "q", "r", "s"));
        intStringMap.put('8', Arrays.asList("t", "u", "v"));
        intStringMap.put('9', Arrays.asList("w", "x", "y", "z"));
        List<String> results = new LinkedList<>();
        for (int i = 0; i < digits.length(); i++) {
            if (intStringMap.containsKey(digits.charAt(i))) {
                List<String> letters = intStringMap.get(digits.charAt(i));
                List<String> currentResults = new LinkedList<>(results);
                results.clear();

                for (String l : letters) {
                    if (currentResults.isEmpty()) {
                        results.add(l);
                    } else {
                        for (String str : currentResults) {
                            results.add(str + l);
                        }
                    }
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        LetterCombinations testClass = new LetterCombinations();
        System.out.println(testClass.letterCombinations("123").toString());
    }
}
