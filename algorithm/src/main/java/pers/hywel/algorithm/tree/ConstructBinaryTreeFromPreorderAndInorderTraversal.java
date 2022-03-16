package pers.hywel.algorithm.tree;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal【Medium】
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 *
 * @Date 2022-03-04
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /**
     * preOrder: [3,9,20,15,7]
     * inOrder: [9,3,15,20,7]
     * 3为根节点， inOrder中3之前的为左树，3之后的后右树。再往下进行递归构建
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildHelp(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode buildHelp(int[] preorder, int startPre, int endPre,
                               int[] inorder, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) return null;
        TreeNode root = new TreeNode(preorder[startPre]);
        if (startPre == endPre) return root;
        int inorderRootIndex = findNodeIndex(preorder[startPre], inorder, startIn, endIn);
        int leftLength = inorderRootIndex - startIn;
        // 构建左子树
        if (leftLength > 0) {
            root.left = buildHelp(preorder, startPre + 1, startPre + leftLength,
                    inorder, startIn, inorderRootIndex - 1);
        }
        // 构建右子树
        if (leftLength < endPre - startPre) {
            root.right = buildHelp(preorder, startPre + leftLength + 1, endPre,
                    inorder, inorderRootIndex + 1, endIn);
        }
        return root;
    }

    private int findNodeIndex(int value, int[] order, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (order[i] == value) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal testObj
                = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = testObj.buildTree(preOrder, inOrder);
        TreeUtils.prettyPrint(root);
    }
}
