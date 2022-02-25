package pers.hywel.algorithm.tree;

import pers.hywel.algorithm.common.PrintUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 103. Binary Tree Zigzag Level Order Traversal【Medium】
 * <p>
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 * <p>
 * <p>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * <p>
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 * <p>
 * Example 3:
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 */
public class ZigzagPrintTree {
    /**
     * 之字打印tree，
     * 使用两stack实现。奇数层存一个stack，偶数层存一个stack
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Stack<TreeNode> oddNodeStack = new Stack<>();
        Stack<TreeNode> evenNodeStack = new Stack<>();

        oddNodeStack.add(root);
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> layerList = new ArrayList<>();
        Stack<TreeNode> curStack = oddNodeStack;
        while (!oddNodeStack.isEmpty() || !evenNodeStack.isEmpty()) {
            TreeNode curNode = curStack.pop();
            if (curNode == null || curNode.val == null) continue;
            layerList.add(curNode.val);
            // 处理奇数层节点，从左往由加，左节点 右节点
            if (curStack == oddNodeStack) {
                if (curNode.left != null) evenNodeStack.push(curNode.left);
                if (curNode.right != null) evenNodeStack.push(curNode.right);
            } else {
                // 处理偶数层节点，从右往左加，右节点 左节点
                if (curNode.right != null) oddNodeStack.push(curNode.right);
                if (curNode.left != null) oddNodeStack.push(curNode.left);
            }
            // 当前层节点处理完，处理下一层
            if (curStack.isEmpty()) {
                result.add(layerList);
                layerList = new ArrayList<>();
                curStack = oddNodeStack.isEmpty() ? evenNodeStack : oddNodeStack;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] treeArray = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeUtils.buildTreeFromArray(treeArray);
        ZigzagPrintTree testObj = new ZigzagPrintTree();
        List<List<Integer>> result = testObj.zigzagLevelOrder(root);
        PrintUtils.printList(result);
    }
}
