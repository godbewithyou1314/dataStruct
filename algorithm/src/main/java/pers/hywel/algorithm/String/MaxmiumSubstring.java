
package pers.hywel.algorithm.String;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxmiumSubstring {
    public static List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();
        int[] start = new int[26];
        int[] end = new int[26];

        Arrays.fill(start, Integer.MAX_VALUE);
        Arrays.fill(end, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            start[s.charAt(i) - 'a'] = Math.min(start[s.charAt(i) - 'a'], i);
            end[s.charAt(i) - 'a'] = Math.max(end[s.charAt(i) - 'a'], i);
        }

        List<Pair<Integer, Integer>> intervals = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (start[i] == Integer.MAX_VALUE) {
                continue;
            }
            int left = start[i], right = end[i];
            boolean valid = true;
            for (int j = left + 1; j < right; j++) {
                if (start[s.charAt(j) - 'a'] < left) {
                    valid = false;
                    break;
                }
                right = Math.max(right, end[s.charAt(j) - 'a']);
            }
            if (valid) {
                intervals.add(new Pair<>(left, right));
            }
        }

        Collections.sort(intervals, (a, b) -> Integer.compare(a.getValue() - a.getKey(), b.getValue() - b.getKey()));
        boolean[] valid = new boolean[intervals.size()];
        Arrays.fill(valid, true);

        for (int i = 0; i < valid.length; i++) {
            if (!valid[i]) {
                continue;
            }
            for (int j = i + 1; j < valid.length; j++) {
                if (intervals.get(j).getKey() <= intervals.get(i).getKey() && intervals.get(i).getValue() <= intervals.get(j).getValue()) {
                    valid[j] = false;
                }
            }
        }
        for (int i = 0; i < valid.length; i++) {
            if (valid[i]) {
                res.add(s.substring(intervals.get(i).getKey(), intervals.get(i).getValue() + 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxNumOfSubstrings("eaaeeaebcb"));
    }
}
