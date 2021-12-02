package pers.hywel.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * BST (Binary Search Tree)相关
 * 1. 已排序数组转换为BST
 * 2. 验证一颗树是否为BST
 * 3. BST转换为已排序数据（中序遍历）
 */
public class BinarySearchTree {

    /**
     * 一、已排序数组转换为BST
     * 数组中间为根，左右不断递归求中值为子根
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        return constructTreeNode(nums, 0, nums.length - 1);
    }

    private static TreeNode constructTreeNode(int[] nums, int left, int right) {
        if (left > right) return null;
        if (left == right) return new TreeNode(nums[left]);
        int middle = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[middle]);
        treeNode.left = constructTreeNode(nums, left, middle - 1);
        treeNode.right = constructTreeNode(nums, middle + 1, right);
        return treeNode;
    }


    /**
     * 二、验证是否为BST
     * <p>
     * * Validate Binary Search Tree
     * * <p>
     * * Solution
     * * Given a binary tree, determine if it is a valid binary search tree (BST).
     * * <p>
     * * Assume a BST is defined as follows:
     * * <p>
     * * The left subtree of a node contains only nodes with keys less than the node's key.
     * * The right subtree of a node contains only nodes with keys greater than the node's key.
     * * Both the left and right subtrees must also be binary search trees.
     * <p>
     * 是否为二叉搜索树
     * 1. 左树全部节点小于根节点
     * 2. 右树全部节点大于根节点
     * 3. 左树，右树都是二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.right, val, upper)) return false;
        if (!helper(node.left, lower, val)) return false;
        return true;
    }


    /**
     * 三、BST转换为已排序数组（中序遍历）
     * <p>
     * 二叉搜索树，中序遍历后，就是从小到大的有序序列
     */
    public List<Integer> getOrderList(TreeNode root) {
        if (root == null) return null;
        List<Integer> result = new LinkedList<>();
        secondOrder(root, result);
        return result;
    }

    /**
     * 中序遍历
     */
    private void secondOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        secondOrder(node.left, result);
        result.add(node.val);
        secondOrder(node.right, result);
    }

}