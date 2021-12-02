
package pers.hywel.algorithm.backtracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 77. Combinations
 * Medium
 *
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 */
public class Combination {

    /**
     * 长度解法
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            List<List<Integer>> appendList = new LinkedList<>();

            for (List<Integer> temp : result) {
                if (temp.size() < k) {
                    temp.add(i);
                    appendList.add(new LinkedList<>(temp));
                    temp.remove((Integer) i);
                }
            }
            result.addAll(appendList);
            List<Integer> temp = new LinkedList<>();
            temp.add(i);
            result.add(temp);
        }
        return result.stream()
                .filter(subList -> subList.size() == k)
                .collect(Collectors.toList());
    }

    /**
     * 回溯解法
     */
    public List<List<Integer>> combineBackTrack(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        containStartList(1, n, k, new ArrayList<>(k), result);
        return result;
    }

    private void containStartList(int start, int n, int k,
                                  List<Integer> tempList, List<List<Integer>> result) {
        if (tempList.size() == k) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i <= n; i++) {
            tempList.add(i);
            containStartList(i + 1, n, k, tempList, result);
            tempList.remove((Integer)i);
        }
    }

    public static void main(String[] args) {
        Combination obj = new Combination();
        List<List<Integer>> result = obj.combine(4, 2);
        for (List<Integer> singleList : result) {
            System.out.print("[");
            for (Integer num : singleList) {
                System.out.print(num + " ");
            }
            System.out.println("]");
        }
    }
}
