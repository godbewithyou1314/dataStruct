/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.String;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * ZigZag字符串返回
 *
 * 【Medium】ZigZag Conversion
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to
 * display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * @author RobertZhang
 * Created on 2019-12-13 14:28
 */
public class ZigZagConversion {

    /**
     * 一个V一个cycle
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        int n = s.length();
        int cycLen = 2 * numRows - 2;

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; ++i) {
            // 循环有多少个V
            for (int j = 0; j + i < n; j += cycLen) {
                // 添加v的左侧数据
                res.append(s.charAt(i+j));
                // 排除第一行和最后一行，添加V的右侧
                if (i != 0 && i != numRows - 1 && j+cycLen-i < n)
                    res.append(s.charAt(j+cycLen-i));
            }
        }

        return res.toString();
    }

    /**
     * w依次循环读取
     * @param s
     * @param numRows
     * @return
     */
    public static String convertByMyself(String s, int numRows) {
        if (numRows < 2 || s.isEmpty())
            return s;
        Map<Integer, java.util.List<Character>> map = new HashMap<>();
        int direction = -1;
        for (int i=0; i<s.length(); i++) {
            List a;
            int key;
            if (i % (numRows-1) == 0) {
                direction = -direction;
                key = i % (2 * (numRows - 1)) == 0 ? 0 : numRows - 1;
                a = map.getOrDefault(key, new LinkedList<>());
                a.add(s.charAt(i));
                map.put(key, a);
                continue;
            }
            if (direction == 1) {
                key = i % (numRows-1);
                a = map.getOrDefault(key, new LinkedList<>());
            } else {
                // go up
                key = (numRows - 1) - (i % (numRows - 1));
                a = map.getOrDefault(key, new LinkedList<>());
            }
            a.add(s.charAt(i));
            map.put(key, a);
        }
        StringBuilder str = new StringBuilder();
        map.values().stream().forEach(list -> {
            list.forEach(str::append);
        });
        return str.toString();
    }

    public static void main(String[] args) {
        String test = "PAYPALISHIRING";
        System.out.println(convert(test, 3));
    }
}
