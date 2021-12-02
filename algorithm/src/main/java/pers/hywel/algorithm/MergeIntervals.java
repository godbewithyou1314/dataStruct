package pers.hywel.algorithm;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 56. Merge Intervals
 * Medium
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        long[] record = new long[2];
        long baseBit = Long.MIN_VALUE - 1;
        for (int[] interval : intervals) {
            if (interval[0] < 63 && interval[1] > 63) {
                long lowTemp = baseBit >>> (63 - interval[0]);
                lowTemp = lowTemp << interval[0];
                record[0] = record[0] | lowTemp;
                long highTemp = baseBit >>> (interval[1] - 64);
                record[1] = record[1] | highTemp;
            } else if (interval[1] <= 63) {
                long temp = baseBit >>> (63 - (interval[1] - interval[0]));
                System.out.println(Long.toBinaryString(temp));
                temp = temp << interval[0];
                System.out.println(Long.toBinaryString(temp));
                record[0] = record[0] | temp;
                System.out.println(Long.toBinaryString(record[0]));
            } else if (interval[0] > 63) {
                long temp = baseBit >>> (63 - (interval[1] - interval[0]));
                temp = temp << interval[0];
                record[1] = record[1] | temp;
            }
        }


        List<Pair<Integer, Integer>> resultList = new LinkedList<>();
        int start = -1, end = -1;
        String lowLong = Long.toBinaryString(record[0]);
        String highLong = Long.toBinaryString(record[1]);
        StringBuilder sb = new StringBuilder();
        sb.append(lowLong);
        sb.reverse();
        for (int i = 0; i < 126; i++) {
            if (lowLong.charAt(i) == '1') {
                if (start == -1) start = i;
            } else if (lowLong.charAt(i) == '0') {
                if (start != -1) {
                    resultList.add(new Pair<>(start, i));
                    start = -1;
                }
            }
        }
        int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            result[i][0] = resultList.get(i).getKey();
            result[i][1] = resultList.get(i).getValue();
        }
        return result;
    }

    public static void main(String[] args) {
//        bitTest();

        MergeIntervals test = new MergeIntervals();
        int[][] nums = new int[][]{{1,4},{4,5}};
        int[][] result = test.merge(nums);
        for (int[] pair : result) {
            System.out.println(String.format("{%d, %d}", pair[0], pair[1]));
        }
    }

    private static void bitTest() {
        long baseBit = Long.MIN_VALUE - 1;
        long bitOp = baseBit;
        System.out.println(Long.toBinaryString(bitOp));
    }
}
