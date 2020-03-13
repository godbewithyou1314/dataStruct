/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.array;

/**
 * Description:
 * 给一个正数数组，算其中最大面积
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical
 * lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue
 * section) the container can contain is 49.
 *
 * Example:
 *  (8---7)
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * @author RobertZhang
 *

 */
public class MostWater {

    /**
     * 方法二 双指针
     * 每次移动短的那根柱子
     */
    public int maxAreaBy(int[] height) {
        int maxArea = 0, startPoint = 0, endPoint = height.length - 1;
        while (startPoint < endPoint) {
            maxArea = Math.max(maxArea,
                    (endPoint - startPoint) *
                            Math.min(height[endPoint] , height[startPoint]));
            if (height[startPoint] < height[endPoint])
                startPoint++;
            else
                endPoint--;
        }
        return maxArea;
    }



        /**
         * Brute Force
         * 方法一： V(i) = Math.max( V(i-1), maxVolumeEndByIndex(i) )
         * @param height
         * @return
         */
    public int maxAreaByBruteForce(int[] height) {
        int[] resultValume = new int[height.length];
        resultValume[0] = 0;
        for (int i=1; i < height.length; i++) {
            resultValume[i] = Math.max(resultValume[i-1], maxVolumeEndByIndex(height, i));
        }
        return resultValume[height.length - 1];
    }

    // 以end柱为结尾的最大面积
    private int maxVolumeEndByIndex(int[] height, int end) {
        int maxVolume = 0;
        for (int i = 0; i<= end; i++) {
            maxVolume = Math.max(maxVolume, (end - i) * Math.min(height[i], height[end]));
        }
        return maxVolume;
    }

    public static void main(String[] args) {
        MostWater mostWater = new MostWater();
        int[] height = new int[]{2,3,10,5,7,8,9};
        System.out.println(mostWater.maxAreaBy(height));
    }

}
