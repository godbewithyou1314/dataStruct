/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.dynamicprogramming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 120. Triangle
 * Medium
 * <p>
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the
 * row below.
 * <p>
 * For example, given the following triangle
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * <p>
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the
 * triangle.
 *
 * @author RobertZhang
 * Created on 2020-05-15
 */
public class Triangle {
    /**
     * 从下往上，筛选最小的子节点加
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
            }
        }
        return A[0];
    }


    public int minimumTotalMy(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() ==0) return 0;
        if (triangle.size() == 1) return  triangle.get(0).get(0);
        Map<Integer, Integer>[] dpRows = new HashMap[triangle.size()];
        for (int i = 0; i < dpRows.length; i++) {
            dpRows[i] = new HashMap<>();
        }
        int min = triangle.get(0).get(0);
        dpRows[0].put(0, min);
        for (int row = 1; row < triangle.size(); row++) {
            int column = 0;
            for (Integer element : triangle.get(row)){
                int value = element;
                if (!dpRows[row - 1].containsKey(column - 1)) value += dpRows[row - 1].get(column);
                else if (!dpRows[row - 1].containsKey(column))  value += dpRows[row - 1].get(column - 1);
                else value += Math.min(dpRows[row - 1].get(column - 1), dpRows[row - 1].get(column));
                dpRows[row].put(column, value);
                if (column == 0) min = value;
                else min = Math.min(min, value);
                column++;
            }
        }
        return min;
    }


}
