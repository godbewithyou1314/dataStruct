package pers.hywel.algorithm.tree;

/**
 * 236. Lowest Common Ancestor of a Binary Tree【Medium】
 * 最小公共祖先
 * <p>
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * Example 3:
 * <p>
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 *
 * @Date 2022-03-11
 */
public class LowestCommonAncestorOfaBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        contain(root, p, q);
        return ancestor;
    }

    private TreeNode ancestor = null;
    private boolean contain(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        // 判断是否是公共祖先
        // 1. 左右子树里都包含 2. 当前节点时，并且左右节点存在某一个
        boolean leftContain = contain(root.left, p, q);
        boolean rightContain = contain(root.right, p, q);
        boolean isCurNode = root.val.equals(p.val) || root.val.equals(q.val);
        if (ancestor == null && ((leftContain && rightContain)
                || ((leftContain || rightContain) && isCurNode))) {
            ancestor = root;
        }
        return isCurNode || leftContain || rightContain;
    }

    public static void main(String[] args) {
        Integer[] treeArray = new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeUtils.buildTreeFromArray(treeArray);
        TreeUtils.prettyPrint(root);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        LowestCommonAncestorOfaBinaryTree testObj = new LowestCommonAncestorOfaBinaryTree();
        TreeNode result = testObj.lowestCommonAncestor(root, p , q);
        if (result == null) {
            System.out.println("null");
        } else {
            System.out.println(result.val);
        }
    }
}
