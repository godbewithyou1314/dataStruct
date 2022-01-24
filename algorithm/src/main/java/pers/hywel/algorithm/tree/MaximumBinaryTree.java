package pers.hywel.algorithm.tree;

/**
 * 654. Maximum Binary Tree 【Medium】
 *
 * You are given an integer array nums with no duplicates.
 * A maximum binary tree can be built recursively from nums using the following algorithm:
 *
 * 1. Create a root node whose value is the maximum value in nums.
 * 2. Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * 3. Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 *
 * @Date 2022-12-27
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildRecursion(0, nums.length, nums);
    }

    // [start, end)
    private TreeNode buildRecursion(int start, int end, int[] nums) {
        if (start >= end) {
            return null;
        }
        int maxIndex = findMaxIndex(start, end, nums);
        TreeNode curNode = new TreeNode(nums[maxIndex]);
        curNode.left = buildRecursion(start, maxIndex, nums);
        curNode.right = buildRecursion(maxIndex + 1, end, nums);
        return curNode;
    }

    private int findMaxIndex(int start, int end, int[] nums) {
        if(start > end) {
            return -1;
        }
        int maxIndex = start;
        for (int i = start; i < end; i++) {
            if (nums[i] > nums[maxIndex]) maxIndex = i;
        }
        return maxIndex;
    }
}
