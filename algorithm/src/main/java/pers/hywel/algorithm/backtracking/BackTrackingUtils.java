package pers.hywel.algorithm.backtracking;

import java.util.List;

/**
 * Description:
 *
 * @author zhangwei111
 * Created on 2021/3/8 8:36 下午
 */
public class BackTrackingUtils {
    public static void printDoubleList(List<List<Integer>> result) {
        for (List<Integer> list : result) {
            for (Integer num : list) {
                System.out.print(num + " -->");
            }
            System.out.println();
        }
    }
}
