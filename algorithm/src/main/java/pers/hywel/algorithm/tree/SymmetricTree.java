package pers.hywel.algorithm.tree;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * 树是否左右对称
 * <p>
 * Example 1:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * <p>
 * Example 2:
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if (left == null || right == null)
            return left == right;
        if (left.val != right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }
    public static void main(String[] args) {
        SymmetricTree testObj = new SymmetricTree();
        Integer[] treeArray = new Integer[]{1, 2, 2, 3, 4, 4, 3};
        TreeNode root = TreeUtils.buildTreeFromArray(treeArray);
        System.out.println(testObj.isSymmetric(root));
    }
}
