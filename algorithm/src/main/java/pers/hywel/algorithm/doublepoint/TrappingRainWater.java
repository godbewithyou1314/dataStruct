package pers.hywel.algorithm.doublepoint;

/**
 * 42. Trapping Rain Water【Hard】
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 * Example 1:
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 *
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * Constraints:
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 * @Todo
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int maxIndex = findMaxIndex(height);
        // 左到右。
        int water = 0;
        int start = 0, end = 0;
        while (end <= maxIndex) {
            if (height[end] >= height[start]) {
                water += calWaterBetween(start, end, height);
                start = end;
            } else {
                water -= height[end];
            }
            end++;
        }

        // 右到左
        start = height.length - 1;
        end = height.length - 1;
        while (start >= maxIndex) {
            if (height[start] >= height[end]) {
                water += calWaterBetween(start, end, height);
                end = start;
            }
            start--;
        }
        return water;
    }

    private int findMaxIndex(int[] height) {
        int max = 0, maxIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] >= max) {
                max = height[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    // [start, end]
    private int calWaterBetween(int start, int end, int[] height) {
        return (end - start) * Math.min(height[start], height[end]);
    }


    public static void main(String[] args) {
        TrappingRainWater testObj = new TrappingRainWater();
        int[] height = new int[]{4,2,0,3,2,5};
        int result = testObj.trap(height);
        System.out.println(result);
    }
}
