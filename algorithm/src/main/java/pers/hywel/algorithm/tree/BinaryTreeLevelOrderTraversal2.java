package pers.hywel.algorithm.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 107. Binary Tree Level Order Traversal II [Medium]
 *
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (i.e., from left to right, level by level from leaf to root).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
public class BinaryTreeLevelOrderTraversal2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        recursionTree(root, new ArrayList<>(), result);
        // 添加头结点
        result.add(Collections.singletonList(root.val));
        return result;
    }

    private void recursionTree(TreeNode root, List<Integer> tempList, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        // 遍历子节点list
        List<Integer> subNodeList = new ArrayList<>();
        recursionTree(root.left, subNodeList, result);
        recursionTree(root.right, subNodeList, result);
        // 当前节点为上一层的子节点
        tempList.add(root.val);
        // add 子节点list到结果
        result.add(subNodeList);
    }

    public static void main(String[] args) {
        Integer[] treeArray = new Integer[]{3,9,20,null,null,15,7};
        TreeNode root = TreeUtils.buildTreeFromArray(treeArray);
        TreeUtils.prettyPrint(root);
    }

}
