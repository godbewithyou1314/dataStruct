package pers.hywel.algorithm.doublepoint;

/**
 * 42. Trapping Rain Water【Hard】
 * <p>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * <p>
 * Example 1:
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 * @Date 2022-03-17
 */
public class TrappingRainWater {
    // 雨水计算： i的位置雨水 = Math.min(leftMax, rightMax) - height[i] 的值
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int result = 0;
        // 保存每个i位置，他左边的最大数
        int[] leftMax = new int[height.length];
        // 保存每个i位置，他右边的最大数
        int[] rightMax = new int[height.length];
        initLeftMaxArray(height, leftMax);
        initRightMaxArray(height, rightMax);
        for (int i = 0; i < height.length; i++) {
            int base = Math.min(leftMax[i], rightMax[i]);
            result += (base - height[i]);
        }
        return result;
    }

    // 从左往右找，找到每个位置左边最大
    private void initLeftMaxArray(int[] height, int[] leftMaxArray) {
        int leftMax = height[0];
        for (int i = 0; i < height.length; i++) {
            if (height[i] > leftMax) {
                leftMaxArray[i] = height[i];
                leftMax = height[i];
            } else {
                leftMaxArray[i] = leftMax;
            }
        }
    }

    // 从右往左找，找到每个位置右边最大
    private void initRightMaxArray(int[] height, int[] rightMaxArray) {
        int rightMax = height[height.length - 1];
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] > rightMax) {
                rightMaxArray[i] = height[i];
                rightMax = height[i];
            } else {
                rightMaxArray[i] = rightMax;
            }
        }
    }

    public static void main(String[] args) {
        TrappingRainWater testObj = new TrappingRainWater();
        int[] height = new int[]{4, 2, 0, 3, 2, 5};
        int result = testObj.trap(height);
        System.out.println(result);
    }
}
