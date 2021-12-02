package pers.hywel.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. Gray Code
 * Medium
 *
 * An n-bit gray code sequence is a sequence of 2n integers where:
 *
 * Every integer is in the inclusive range [0, 2n - 1],
 * The first integer is 0,
 * An integer appears no more than once in the sequence,
 * The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * The binary representation of the first and last integers differs by exactly one bit.
 * Given an integer n, return any valid n-bit gray code sequence.
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: [0,1]
 *
 * Constraints:
 *
 * 1 <= n <= 16
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> notZeroStartList = new ArrayList<>(n);
        dfs(n, new ArrayList<>(), notZeroStartList);
        List<Integer> temp = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        List<Integer> point = temp;
        for (int i : notZeroStartList) {
            if (i == 0) point = result;
            point.add(i);
        }
        result.addAll(temp);
        return result;
    }

    private void dfs(int n, List<Integer> temp, List<Integer> result) {
        if (n == 0) {
            result.add(list2Integer(temp));
            return;
        }
        if (temp.isEmpty() || temp.get(temp.size() - 1) == 1) {
            temp.add(0);
            dfs(n - 1, temp, result);
            temp.remove(temp.size() - 1);
            temp.add(1);
        } else {
            temp.add(1);
            dfs(n - 1, temp, result);
            temp.remove(temp.size() - 1);
            temp.add(0);
        }
        dfs(n - 1, temp, result);
        temp.remove(temp.size() - 1);
    }

    private Integer list2Integer(List<Integer> tempList) {
        int r = 0;
        for (int i = 0; i < tempList.size(); i++) {
            int t = tempList.get(i);
            r += ( t << (tempList.size() - i - 1));
        }
        return r;
    }

    public static void main(String[] args) {
        GrayCode testObj = new GrayCode();
        List<Integer> result = testObj.grayCode(3);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
