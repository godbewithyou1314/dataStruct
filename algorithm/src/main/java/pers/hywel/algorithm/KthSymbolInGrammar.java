/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm;

/**
 * Description:
 * 779. [Medium] K-th Symbol in Grammar
 *
 * On the first row, we write a 0. Now in every subsequent row,
 * we look at the previous row and replace each occurrence of 0 with 01,
 * and each occurrence of 1 with 10.
 *
 * Given row N and index K, return the K-th indexed symbol in row N.
 * (The values of K are 1-indexed.) (1 indexed).
 *
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 2
 * Output: 1
 *
 * Input: N = 4, K = 5
 * Output: 1
 *
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 * row 5: 0110100110010110
 * Note:
 *
 * N will be an integer in the range [1, 30].
 * K will be an integer in the range [1, 2^(N-1)].
 *
 * @author zhangwei
 * Created on 2021/1/7 8:28 下午
 */
public class KthSymbolInGrammar {

    public static void main(String[] args) {
        System.out.println(1 << 30);
        System.out.println("开始时间：" + System.currentTimeMillis());
        System.out.println(kthGrammar(4, 5));
        System.out.println("结束时间：" + System.currentTimeMillis());
    }

    public static int kthGrammar(int n, int k) {
        if (n == 1) {
            return 1 - (k % 2);
        }
        if (k % 2 == 0)
            return 1 - kthGrammar(n - 1, (k + 1) / 2);
        else
            return kthGrammar(n - 1, (k + 1) / 2);
    }

    public static int kthGrammarOld(int N, int K) {
        int n = 1;
        while ( 1 << (n - 1) < K) {
            n++;
        }
        boolean[] result = new boolean[1 << (n - 1)];
        for (int i = 0; i < n; i++) {
            genString(result, i);
        }
        return result[K - 1] ? 1 : 0;
    }

    private static void genString(boolean[] result, int n) {
        if (n == 0) {
            result[0] = false;
            return;
        } else if (n == 1) {
            result[0] = false;
            result[1] = true;
            return;
        }
        for (int i = (1 << (n - 2)); i < (1 << (n - 1)); i++) {
            result[2 * i] = result[i];
            result[2 * i + 1] = !result[i];
        }
    }
}
