/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.integer;

/**
 * Description:
 * Integer to Roman 整数转罗马数字表示
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII,
 * which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not
 * IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction
 * is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * <p>
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: 3
 * Output: "III"
 * <p>
 * Example 2:
 * Input: 4
 * Output: "IV"
 * <p>
 * Example 3:
 * Input: 9
 * Output: "IX"
 * <p>
 * Example 4:
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 *
 * @author zhangwei111
 * Created on 2019-12-27 14:57
 */
public class Integer2Roman {

    /**
     * 方法2， 字典搞定
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 方法一
     *
     * @param num
     *
     * @return
     */
    public String intToRomanByMyself(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 900) {
            num = appendSymbol(sb, "M", 1000, num, "C", 100);
        }
        if (num >= 400) {
            num = appendSymbol(sb, "D", 500, num, "C", 100);
        }
        if (num >= 90) {
            num = appendSymbol(sb, "C", 100, num, "X", 10);
        }
        if (num >= 40) {
            num = appendSymbol(sb, "L", 50, num, "X", 10);
        }
        if (num >= 9) {
            num = appendSymbol(sb, "X", 10, num, "I", 1);
        }
        if (num >= 4) {
            num = appendSymbol(sb, "V", 5, num, "I", 1);
        }
        while (num > 0) {
            sb.append("I");
            num -= 1;
        }
        return sb.toString();
    }

    private int appendSymbol(StringBuilder sb, String symbol, int value, int num, String speSymbol, int speValue) {
        while (num >= (value - speValue)) {
            if (num < value) {
                sb.append(speSymbol);
                sb.append(symbol);
                num -= (value - speValue);
            } else {
                sb.append(symbol);
                num -= value;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Integer2Roman thisClass = new Integer2Roman();
        int test = 4;
        System.out.println(thisClass.intToRoman(test));
    }
}
