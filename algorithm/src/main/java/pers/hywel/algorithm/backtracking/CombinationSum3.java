
package pers.hywel.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * 216. Combination Sum III
 * Medium
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 *
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 *
 * Example 3:
 *
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations. [1,2,1] is not valid because 1 is used twice.
 *
 * @author RobertZhang
 * Created on 2021/3/8 7:53 下午
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backTracking(1, 0, k, n, new ArrayList<>(), result);
        return result;
    }

    // 深度搜索：
    // 1 + 2 + 3 + ...
    // 2 + 3 + 4 + ...
    // 3 + 4 + 5 + ...
    // 4 + 5 + 6 + ...
    // 成功条件：得到n值
    // 退出条件：相机数字和已经大于n，或者累计数字个数已经超过k。都可以直接结束往下搜索
    public void backTracking(int start, int tempSum, int k, int n, List<Integer> cur, List<List<Integer>> result) {
        if (tempSum == n && cur.size() == k) {
            result.add(new ArrayList<>(cur));
        }
        if (tempSum > n || cur.size() > k) {
            return;
        }
        for (int i = start; i < 10; i++) {
            tempSum += i;
            cur.add(i);
            backTracking(i + 1, tempSum, k, n, cur, result);
            tempSum -= i;
            cur.remove(cur.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum3 test = new CombinationSum3();
        List<List<Integer>> result = test.combinationSum3(3, 7);
        BackTrackingUtils.printDoubleList(result);
    }


}
