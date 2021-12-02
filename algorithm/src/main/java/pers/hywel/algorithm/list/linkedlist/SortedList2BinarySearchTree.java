package pers.hywel.algorithm.list.linkedlist;

import pers.hywel.algorithm.list.common.ListNode;
import pers.hywel.algorithm.list.common.TreeNode;

/**
 * Description:
 * 109. Convert Sorted List to Binary Search Tree
 * Medium
 *
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
 * of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted linked list: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * @author RobertZhang
 */
public class SortedList2BinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode fastPtr = head, slowPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        ListNode middle = slowPtr;
        TreeNode root = new TreeNode(middle.val);
        root.left = reverseLeftByRecur(head, middle, null);
        root.right = getRightByRecur(middle.next, root);
        return root;
    }

    /**
     * 左树
     * @param head
     * @param middle
     * @param treeNode
     * @return
     */
    private TreeNode reverseLeftByRecur(ListNode head, ListNode middle, TreeNode treeNode) {
        if (head == middle) {
            return treeNode;
        }
        ListNode next = head.next;
        TreeNode curNode = new TreeNode(head.val);

        TreeNode parentNode = reverseLeftByRecur(next, middle, treeNode);

        parentNode.left = curNode;
        return null;
    }

    private TreeNode getRightByRecur(ListNode head, TreeNode treeNode) {
        TreeNode curTreeNode = treeNode;
        while (head != null) {
            curTreeNode.right = new TreeNode(head.val);
            curTreeNode = curTreeNode.right;
            head = head.next;
        }
        return treeNode;
    }

}
