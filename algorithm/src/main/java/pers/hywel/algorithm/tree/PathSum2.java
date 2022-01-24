package pers.hywel.algorithm.tree;

import pers.hywel.algorithm.common.PrintUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. Path Sum II [Medium]
 * <p>
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
 * where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of the node values, not node references.
 * A root-to-leaf path is a path starting from the root and ending at any leaf node.
 * A leaf is a node with no children.
 * <p>
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * <p>
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * <p>
 * Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * <p>
 * <p>
 * 必须从根节点到叶子节点。。。。
 */
public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new LinkedList<>();
        recursionTree(root, 0, targetSum, new ArrayList<>(), result);
        return result;
    }

    // 先序遍历 + dfs
    private void recursionTree(TreeNode root, int currentSum, int targetSum, List<Integer> tempList, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        currentSum += root.val;
        tempList.add(root.val);
        // 必须为根节点到叶子节点的和
        if (currentSum == targetSum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(tempList));
        }
        recursionTree(root.left, currentSum, targetSum, tempList, result);
        recursionTree(root.right, currentSum, targetSum, tempList, result);
        tempList.remove(tempList.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = genTree();
        PathSum2 testObj = new PathSum2();
        List<List<Integer>> result = testObj.pathSum(root, 22);
        PrintUtils.printList(result);
    }

    private static TreeNode genTree() {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(11);
        node1.left = node3;
        node1.right = null;
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        node2.left = node4;
        node2.right = node5;

        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        node3.left = node6;
        node3.right = node7;
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);
        node5.left = node8;
        node6.right = node9;
        return root;
    }
}
