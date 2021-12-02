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
 * row(n) = row(n-1) + func(row(n-1)后半段)
 * row(n, k) = k < row(n-1).size() ? row(n - 1, k) : func(row(n-1)后半段, k - row(n-1).size() / 2)
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
}
