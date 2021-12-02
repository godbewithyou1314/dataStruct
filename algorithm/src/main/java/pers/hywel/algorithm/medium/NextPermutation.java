package pers.hywel.algorithm.medium;

/**
 * Description:
 * Next Permutation
 * 实现按照字典序，比当前大一个的组合
 * <p>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
 * order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
 * column.
 * <p>
 * 5,1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @author RobertZhang
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = nums.length - 1; i > 0; i--) {
            // 前一个数小于后一个数，直接交换则是结果
            if (nums[i - 1] < nums[i]) {
                swap(nums, i - 1, i);
                return;
            }
            // 示例 2，4，3，1 --> 2的子序列，是最大序列
            // 翻转子序列，然后找到第二大的和2交换位置则是结果
            if (i - 2 >= 0 && nums[i - 2] < nums[i - 1]) {
                reverse(nums, i - 1, nums.length - 1);
                for (int j = i - 1; j < nums.length; j++) {
                    if (nums[j] > nums[i - 2]) {
                        swap(nums, i -2, j);
                        return;
                    }
                }
            }
        }
        // 已经是最大序列情况
        reverse(nums, 0, nums.length - 1);
    }

    private void swap(int[] nums, int a, int b) {
        int swap = nums[a];
        nums[a] = nums[b];
        nums[b] = swap;
    }

    private void reverse(int[] nums, int start, int end) {
        for (int i = 0; i <= (end - start) / 2; i++) {
            swap(nums, start + i, end - i);
        }
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();

        NextPermutation testClass = new NextPermutation();
        int[] nums = {2, 3, 1};
        testClass.nextPermutation(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }

        Long endTime = System.currentTimeMillis();
        System.out.println("运行耗时：" + (endTime - startTime) + "ms");
    }

}
