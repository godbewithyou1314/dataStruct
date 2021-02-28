/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.integer;

/**
 * 首先给出前10个数字的序列：
 *
 * 1. 1
 * 2. 11
 * 3. 21
 * 4. 1211
 * 5. 111221
 * 6. 312211
 * 7. 13112221
 * 8. 1113213211
 * 9. 31131211131221
 * 10. 13211311123113112211
 * 题意就是
 *
 * n=1时，输出字符串1；
 *
 * n=2时，数上次字符串中各个数值的个数，因为上个数字字符串中有1个1，所以输出11；
 *
 * n=3时，由于上个字符串是11，有2个1，所以输出21；
 *
 * n=4时，由于上个数字的字符串是21，有1个2和1个1，所以输出1211，依次类推......
 */
public class CountAndSay {
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        String preString = countAndSay(n - 1);
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < preString.length();) {
            int j = i + 1;
            while (j < preString.length() && preString.charAt(j) == preString.charAt(i)) j++;
            resultBuilder.append(j - i);
            resultBuilder.append(preString.charAt(i));
            i = j;
        }
        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(6));
    }


    // old deprecated
    private static String countRecursion(String str) {
        StringBuilder strNew = new StringBuilder();
        int nums = 1;
        char preChar = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == preChar) nums++;
            else {
                strNew.append(nums);
                strNew.append(preChar);
                nums = 1;
                preChar = str.charAt(i);
            }
        }
        strNew.append(nums);
        strNew.append(preChar);
        return strNew.toString();
    }
}
