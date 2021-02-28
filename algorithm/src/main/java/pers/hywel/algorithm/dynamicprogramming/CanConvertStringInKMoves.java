/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.dynamicprogramming;

/**
 * Description:
 * 1540. Can Convert String in K Moves
 * Medium
 * Given two strings s and t, your goal is to convert s into t in k moves or less.
 * <p>
 * During the ith (1 <= i <= k) move you can:
 * <p>
 * Choose any index j (1-indexed) from s, such that 1 <= j <= s.length and j has not been chosen in any previous move, and shift the character at that index i times.
 * Do nothing.
 * Shifting a character means replacing it by the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Shifting a character by i means applying the shift operations i times.
 * <p>
 * Remember that any index j can be picked at most once.
 * <p>
 * Return true if it's possible to convert s into t in no more than k moves, otherwise return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "input", t = "ouput", k = 9
 * Output: true
 * Explanation: In the 6th move, we shift 'i' 6 times to get 'o'. And in the 7th move we shift 'n' to get 'u'.
 *
 * @author RobertZhang
 * Created on 2021/2/25 7:24 下午
 */
public class CanConvertStringInKMoves {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) return false;
        // 初始化操作数组，operators[1] 表示可以进行1次shift的次数。对应 k = 1 、27...
        int[] operators = new int[Math.min(k, 25) + 1];
        for (int i = 1; i < operators.length; i++) {
            operators[i] = k / 26;
            if (i <= k % 26) operators[i] += 1;
        }
        // s里的每个字符和t对应字符比较，判断operators数组里是否还余有操作次数
        for (int i = 0; i < s.length(); i++) {
            if (t.charAt(i) != s.charAt(i)) {
                int diff = t.charAt(i) - s.charAt(i);
                diff = diff < 0 ? 26 + diff : diff;
                if (diff > k || operators[diff] == 0) {
                    return false;
                } else {
                    operators[diff] -= 1;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CanConvertStringInKMoves test = new CanConvertStringInKMoves();
        String s = "a";
        String t = "b";
        int k = 25;
        System.out.println(test.canConvertString(s, t, k));
    }

}
